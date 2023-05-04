import java.util.Queue;

public class ConsumerSafe implements Runnable {
    final Queue<Integer> queue;
    int N;

    public ConsumerSafe(Queue<Integer> queue, int N) {
        this.queue = queue;
        this.N = N;
    }

    @Override
    public void run() {
        int count = 0;
        while (count < this.N) {
            Integer integer;
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait(); // Wait for producers to add objects
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                integer = queue.remove();
            }
            // Consume obj here
            count++;
        }
    }
}
