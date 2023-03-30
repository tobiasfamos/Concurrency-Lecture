import counters.CounterAtomic;
import counters.CounterFilter;
import counters.CounterLock;
import counters.CounterMonitor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CounterEvaluation {
    public static void main(String[] args) {

        List<String> executionTimesAtomic = new ArrayList<>();
        List<String> executionTimesFilter = new ArrayList<>();
        List<String> executionTimesMonitor = new ArrayList<>();
        List<String> executionTimesLock = new ArrayList<>();
        String[][] parameters = {{"2","10000000"},{"4","10000000"},{"8","10000000"},{"16","10000000"},};
        for (String[] parameter: parameters) {
            long start = System.currentTimeMillis();
            CounterAtomic.main(parameter);
            long end = System.currentTimeMillis();
            executionTimesAtomic.add(String.format("%s,%d", parameter[0], end-start));
        }
        for (String[] parameter: parameters) {
            long start = System.currentTimeMillis();
            CounterFilter.main(parameter);
            long end = System.currentTimeMillis();
            executionTimesFilter.add(String.format("%s,%d", parameter[0], end-start));
        }
        for (String[] parameter: parameters) {
            long start = System.currentTimeMillis();
            CounterMonitor.main(parameter);
            long end = System.currentTimeMillis();
            executionTimesMonitor.add(String.format("%s,%d", parameter[0], end-start));
        }
        for (String[] parameter: parameters) {
            long start = System.currentTimeMillis();
            CounterLock.main(parameter);
            long end = System.currentTimeMillis();
            executionTimesLock.add(String.format("%s,%d", parameter[0], end-start));
        }
        System.out.println("Counter Atomic");
        System.out.println(String.join("\n", executionTimesAtomic));
        System.out.println("Counter Filter");
        System.out.println(String.join("\n", executionTimesFilter));
        System.out.println("Counter Monitor");
        System.out.println(String.join("\n", executionTimesMonitor));
        System.out.println("Counter Lock");
        System.out.println(String.join("\n", executionTimesLock));
    }

}
