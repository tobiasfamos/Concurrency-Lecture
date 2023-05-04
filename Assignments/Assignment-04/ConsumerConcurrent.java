import java.util.Queue;

public class ConsumerConcurrent implements Runnable {
    final Queue<Integer> queue;
    int N;

    public ConsumerConcurrent(Queue<Integer> queue, int N) {
        this.queue = queue;
        this.N = N;
    }

    @Override
    public void run() {
        int count = 0;
        while (count < N) {
            Object obj = queue.poll();
            if (obj != null) {
                count++;
            }
        }
    }
}
