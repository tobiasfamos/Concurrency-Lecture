import java.util.Queue;

public class ProducerUnsafe implements Runnable {
    final Queue<Integer> queue;
    int N;

    public ProducerUnsafe(Queue<Integer> queue, int N) {
        this.queue = queue;
        this.N = N;
    }

    @Override
    public void run() {
        for (int j = 0; j < this.N; j++) {
            Integer integer = j;
            this.queue.add(integer);
        }
    }
}
