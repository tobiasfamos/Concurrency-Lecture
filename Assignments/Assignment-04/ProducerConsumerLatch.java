import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.CountDownLatch;

public class ProducerConsumerLatch {
    public static void main(String[] args) throws InterruptedException {
        int T = Integer.parseInt(args[0]);
        int N = Integer.parseInt(args[1]);

        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        Thread[] producers = new Thread[T];
        Thread[] consumers = new Thread[T];
        CyclicBarrier barrier = new CyclicBarrier(T);
        CountDownLatch latch = new CountDownLatch(T);

        // Start T producer threads
        for (int i = 0; i < T; i++) {
            producers[i] = new Thread(new ProducerLatch(queue, N, latch, barrier));
            producers[i].start();
        }

        // Start T consumer threads
        for (int i = 0; i < T; i++) {
            consumers[i] = new Thread(new ConsumerLatch(queue, N, T, latch));
            consumers[i].start();
        }

        // Wait for all threads to finish
        for (int i = 0; i < T; i++) {
            producers[i].join();
            consumers[i].join();
        }
    }
}
