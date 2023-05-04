import java.util.Queue;

import static java.lang.Thread.sleep;

public class ConsumerUnsafe implements Runnable {
    final Queue<Integer> queue;
    int N;
    int id;

    public ConsumerUnsafe(Queue<Integer> queue, int N, int id) {
        this.queue = queue;
        this.N = N;
        this.id = id;
    }

    @Override
    public void run() {

        int count = 0;
        while (count < N) {
            Integer integer = queue.poll();
            // Consume obj here
            count++;
        }
    }
}

