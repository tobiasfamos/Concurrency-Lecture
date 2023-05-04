import java.util.Queue;

import static java.lang.Thread.sleep;

public class ConsumerUnsafe implements Runnable {
    final Queue<Integer> queue;
    int N;

    public ConsumerUnsafe(Queue<Integer> queue, int N) {
        this.queue = queue;
        this.N = N;
    }

    @Override
    public void run() {
        int count = 0;
        while (count < this.N) {
            Integer integer;
            while (queue.isEmpty()) {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            integer = queue.remove();

            // Consume obj here
            count++;
        }
    }
}
