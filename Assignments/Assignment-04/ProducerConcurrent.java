import java.util.Queue;

public class ProducerConcurrent implements Runnable{
    final Queue<Integer> queue;
    int N;

    public ProducerConcurrent(Queue<Integer> queue, int N) {
        this.queue = queue;
        this.N = N;
    }

    @Override
    public void run() {
        for (int j = 0; j < N; j++) {
            Integer integer = j;
            queue.add(integer);
        }
    }

}
