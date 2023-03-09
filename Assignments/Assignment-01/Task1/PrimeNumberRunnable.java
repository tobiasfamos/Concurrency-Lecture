public class PrimeNumberRunnable implements Runnable {

    private long start;
    private long end;
    private int threadID;

    public PrimeNumberRunnable(int threadID,long start, long end) {
        this.start = start;
        this.end = end;
        this.threadID = threadID;
    }

    @Override
    public void run() {
        this.checkPrimeNumbers();
    }

    private void checkPrimeNumbers() {
        for (long currentPrimeNumber = start; currentPrimeNumber <= end; currentPrimeNumber++) {
            checkPrimeNumber(currentPrimeNumber);
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