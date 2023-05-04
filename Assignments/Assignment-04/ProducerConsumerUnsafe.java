import java.util.Queue;
import java.util.LinkedList;

public class ProducerConsumerUnsafe {
    public static void main(String[] args) throws InterruptedException {
        // int T = Integer.parseInt(args[0]);
        // int N = Integer.parseInt(args[1]);
        int T = 10;
        int N = 200;
        Queue<Integer> queue = new LinkedList<>();
        Thread[] producers = new Thread[T];
        Thread[] consumers = new Thread[T];

        // Start T producer threads
        for (int i = 0; i < T; i++) {
            ProducerUnsafe prod = new ProducerUnsafe(queue, N);
            producers[i] = new Thread(prod);
            producers[i].start();
        }

        // Start T consumer threads
        for (int i = 0; i < T; i++) {
            ConsumerUnsafe cons = new ConsumerUnsafe(queue, N);
            consumers[i] = new Thread(cons);
            consumers[i].start();
        }

        // Wait for all threads to finish
        for (int i = 0; i < T; i++) {
            producers[i].join();
            consumers[i].join();
        }
    }
}
