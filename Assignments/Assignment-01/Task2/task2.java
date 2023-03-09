package Task2;
import java.util.ArrayList;
import java.util.List;

public class task2 {

    public static void main(String[] args) throws InterruptedException {
        long[] checkUpTo = new long[] { 1000000, 10000000 };
        int[] number_of_threads = new int[] { 1, 2, 4, 8, 16 };
        List<String> executionTimes = new ArrayList<String>();

        for (int threadcount : number_of_threads) {
            for (long upTo : checkUpTo) {
                long executionTime = MultiThreadPrimeCheck.checkPrimesWithCounter(threadcount, upTo);
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
