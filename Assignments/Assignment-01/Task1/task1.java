import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Write a Java program that takes as arguments two integers, T and N, and forks T threads that will together search for and print all primes between 1 and N. The program should evenly split the range [1..N] into T sub-ranges assigned to each thread.
// Execute the program with N={10'000'000,100'000'000} and T={1,2,4,8,16}. Report execution times.

class Task1 {
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

    public static void main(String[] args) throws InterruptedException {
        long[] checkUpTo = new long[] { 1000000, 10000000 };
        int[] number_of_threads = new int[] { 1, 2, 4, 8, 16 };
        List<String> executionTimes = new ArrayList<String>();

        for (int threadcount : number_of_threads) {
            for (long upTo : checkUpTo) {
                long executionTime = Task1.checkPrimes(threadcount, upTo);
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
