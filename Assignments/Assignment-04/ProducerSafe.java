import java.util.Queue;

public class ProducerSafe implements Runnable {
    final Queue<Integer> queue;
    int N;

    public ProducerSafe(Queue<Integer> queue, int N) {
        this.queue = queue;
        this.N = N;
    }

    @Override
    public void run() {
        for (int j = 0; j < this.N; j++) {
            Integer integer = j;
            synchronized (this.queue) {
                this.queue.add(integer);
                this.queue.notifyAll();
            }
        }
    }

}
