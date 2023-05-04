import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Benchmark {
    /*
    Output of the Benchmark program:
    Elapsed time for Unsafe: 2029 ms
    Elapsed time for Safe: 6724 ms
    Elapsed time for Concurrent: 1297 ms
    Elapsed time for Latch: 1387 ms
     */
    public static void main(String[] args) throws InterruptedException {
        int T = 2;
        int N = 10000000;

        long startTime = System.nanoTime();
        ProducerConsumerUnsafe.main(new String[]{Integer.toString(T), Integer.toString(N)});
        long endTime = System.nanoTime();
        long durationInMillis = TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);
        System.out.println("Elapsed time for Unsafe: " + durationInMillis + " ms");

        startTime = System.nanoTime();
        ProducerConsumerSafe.main(new String[]{Integer.toString(T), Integer.toString(N)});
        endTime = System.nanoTime();
        durationInMillis = TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);
        System.out.println("Elapsed time for Safe: " + durationInMillis + " ms");

        startTime = System.nanoTime();
        ProducerConsumerConcurrent.main(new String[]{Integer.toString(T), Integer.toString(N)});
        endTime = System.nanoTime();
        durationInMillis = TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);
        System.out.println("Elapsed time for Concurrent: " + durationInMillis + " ms");

        startTime = System.nanoTime();
        ProducerConsumerLatch.main(new String[]{Integer.toString(T), Integer.toString(N)});
        endTime = System.nanoTime();
        durationInMillis = TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);
        System.out.println("Elapsed time for Latch: " + durationInMillis + " ms");
    }
}
