package Task2;
public class PrimeNumberSharedCounterRunnable implements Runnable {

    private int threadID;
    private long end;
    private SynchronizedCounter counter;

    public PrimeNumberSharedCounterRunnable(int threadID, SynchronizedCounter counter, long end) {
        this.end = end;
        this.threadID = threadID;
        this.counter = counter;
    }

    @Override
    public void run() {
        this.checkPrimeNumbers();
    }

    private void checkPrimeNumbers() {
        long numberToCheck = this.counter.increaseAndGet();
        while (numberToCheck <= this.end){
            this.checkPrimeNumber(numberToCheck);
            numberToCheck = this.counter.increaseAndGet();
        }
    }

    private void checkPrimeNumber(long numberToCheck) {
        // Source of code: https://www.javatpoint.com/prime-number-program-in-java
        long i, m = 0, flag = 0;
        m = numberToCheck / 2;
        if (numberToCheck == 0 || numberToCheck == 1) {
            return;
        } else {
            for (i = 2; i <= m; i++) {
                if (numberToCheck % i == 0) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                System.out.println(String.format("T:%d Found prime: %d", this.threadID, numberToCheck));
            }
        }
    }

}