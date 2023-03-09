import java.util.Date;

public class MultiThreadPrimeCheck {

    public static Long checkPrimes(int numberOfThreads, long checkUptTo) throws InterruptedException {
        Thread[] threads = new Thread[numberOfThreads];
        for (int i = 0; i < threads.length; i++) {
            long start = (checkUptTo / numberOfThreads) * i;
            long end = (checkUptTo / numberOfThreads) * (i + 1);
            System.out.println(start + ": " + end);
            threads[i] = new Thread(new PrimeNumberRunnable(i, start, end));
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
        Long time = MultiThreadPrimeCheck.checkPrimes(10, 10000);
        System.out.println("Took " + time + " Milliseconds");
    }
}
