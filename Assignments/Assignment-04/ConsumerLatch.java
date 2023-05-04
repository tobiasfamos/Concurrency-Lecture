import java.util.concurrent.CountDownLatch;
import java.util.Queue;

public class ConsumerLatch implements Runnable{
    final Queue<Integer> queue;
    int N;
    int T;
    CountDownLatch latch;

    public ConsumerLatch(Queue<Integer> queue, int N, int T, CountDownLatch latch) {
        this.queue = queue;
        this.N = N;
        this.T = T;
        this.latch = latch;
    }
    @Override
    public void run() {
        int count = 0;
        while (count < N) {
            Object obj = queue.poll();
            if (obj != null) {
                // Consume obj here
                count++;
            }
        }
        latch.countDown();
    }
}
