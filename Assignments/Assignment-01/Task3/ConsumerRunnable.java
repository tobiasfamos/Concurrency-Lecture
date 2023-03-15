public class ConsumerRunnable implements Runnable {

    private int numberOfConsumes;
    private SynchronizedBuffer buffer;

    public ConsumerRunnable(int numberOfConsumes, SynchronizedBuffer buffer){
        this.numberOfConsumes = numberOfConsumes;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.numberOfConsumes; i++) {
            try {
                this.buffer.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
    
}
