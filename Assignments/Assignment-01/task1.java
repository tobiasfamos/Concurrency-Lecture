import java.util.ArrayList;
import java.util.List;

// Write a Java program that takes as arguments two integers, T and N, and forks T threads that will together search for and print all primes between 1 and N. The program should evenly split the range [1..N] into T sub-ranges assigned to each thread.
// Execute the program with N={10'000'000,100'000'000} and T={1,2,4,8,16}. Report execution times.

class Task1 {
    int id;

    public static void main(String[] args) throws InterruptedException {
        long[] checkUpTo = new long[] { 1000000, 10000000 };
        int[] number_of_threads = new int[] { 1, 2, 4, 8, 16 };
        List<String> executionTimes = new ArrayList<String>();

        for (int threadcount : number_of_threads) {
            for (long upTo : checkUpTo) {
                long executionTime = MultiThreadPrimeCheck.checkPrimes(threadcount, upTo);
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
