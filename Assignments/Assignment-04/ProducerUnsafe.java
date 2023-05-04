import java.util.Queue;

public class ProducerUnsafe implements Runnable {
    final Queue<Integer> queue;
    int N;
    int id;

    public ProducerUnsafe(Queue<Integer> queue, int N, int id) {
        this.queue = queue;
        this.N = N;
        this.id = id;
    }

    @Override
    public void run() {
        for (int j = 0; j < this.N; j++) {
            Integer integer = j;
            this.queue.add(integer);
        }
    }
}
