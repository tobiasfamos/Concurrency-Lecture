import counters.CounterBakery;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class EvaluateLocks {

    public static void main(String[] args) {
        int t = (args.length >= 1 ? Integer.parseInt(args[0]) : 2);
        int n = (args.length >= 2 ? Integer.parseInt(args[1]) : 5);

        ReadWriteLock readWriteLock = new ReadWriteLockStarvationFree();
        Resource resource = new Resource();

        System.out.println("Start with " + t + " readers and writers");
        // Create threads
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            threads.add(new Thread(new Reader(n, readWriteLock, resource, i)));
            threads.add(new Thread(new Writer(n, readWriteLock, resource, i)));
        }
        // Start threads
        for (int i = 0; i < 2*t; i++) {
            threads.get(i).start();
        }
        // Wait for threads completion
        for (int i = 0; i < 2*t; i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
            }
        }
    }

}
