import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class ProducerLatch implements Runnable{
    final Queue<Integer> queue;
    int N;
    int id;
    CountDownLatch latch;
    CyclicBarrier barrier;

    public ProducerLatch(Queue<Integer> queue, int N, CountDownLatch latch, CyclicBarrier barrier){
        this.queue = queue;
        this.N = N;
        this.latch = latch;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            this.barrier.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (int j = 0; j < N; j++) {
            Integer integer = j;
            queue.add(integer);
        }
        latch.countDown();
    }
}
