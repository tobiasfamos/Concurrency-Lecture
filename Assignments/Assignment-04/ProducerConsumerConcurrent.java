import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ProducerConsumerConcurrent {
    public static void main(String[] args) throws InterruptedException {
        //int T = Integer.parseInt(args[0]);
        //int N = Integer.parseInt(args[1]);
        int T = 10;
        int N  = 2000;
        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        Thread[] producers = new Thread[T];
        Thread[] consumers = new Thread[T];

        for (int i = 0; i < T; i++) {
            producers[i] = new Thread(new ProducerConcurrent(queue, N));
            producers[i].start();
        }

        // Start T consumer threads
        for (int i = 0; i < T; i++) {
            consumers[i] = new Thread(new ConsumerConcurrent(queue, N));
            consumers[i].start();
        }

        // Wait for all threads to finish
        for (int i = 0; i < T; i++) {
            producers[i].join();
            consumers[i].join();
        }
        System.out.println(queue.toString());
    }
}
