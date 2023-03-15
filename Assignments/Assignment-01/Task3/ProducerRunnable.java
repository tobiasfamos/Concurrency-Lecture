public class ProducerRunnable implements Runnable {

    private int numberOfProduces;
    private SynchronizedBuffer buffer;

    public ProducerRunnable(int numberOfProduces, SynchronizedBuffer buffer){
        this.numberOfProduces = numberOfProduces;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.numberOfProduces; i++) {
            try {
                this.buffer.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
    
}
