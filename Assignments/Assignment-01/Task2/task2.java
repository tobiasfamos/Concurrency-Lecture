import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class task2 {

    public static Long checkPrimesWithCounter(int numberOfThreads, long checkUptTo) throws InterruptedException {
        Thread[] threads = new Thread[numberOfThreads];
        SynchronizedCounter counter = new SynchronizedCounter();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new PrimeNumberSharedCounterRunnable(i, counter, checkUptTo));
        }
        long startMilliseconds = new Date().getTime();

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        long endMilliseconds = new Date().getTime();

        return endMilliseconds - startMilliseconds;
    }

    public static void main(String[] args) throws InterruptedException {
        long[] checkUpTo = new long[] { 1000000, 10000000 };
        int[] number_of_threads = new int[] { 1, 2, 4, 8, 16 };
        List<String> executionTimes = new ArrayList<String>();

        for (int threadcount : number_of_threads) {
            for (long upTo : checkUpTo) {
                long executionTime = task2.checkPrimesWithCounter(threadcount, upTo);
                String time = String.format("T: %d  N: %d   Execution time: %d", threadcount, upTo, executionTime);
                executionTimes.add(time);
                System.out.println(time);
            }
        }

        for (String string : executionTimes) {
            System.out.println(string);
        }
    }

}
