import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable {
    private final int philosopherId;
    private int numberOfBitesLeft;
    private final Semaphore leftFork;
    private final Semaphore rightFork;

    public Philosopher(int id, int numberOfBites, Semaphore leftFork, Semaphore rightFork) {
        this.philosopherId = id;
        this.numberOfBitesLeft = numberOfBites;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private boolean hasBitesLeft() {
        return this.numberOfBitesLeft > 0;
    }

    @Override
    public void run() {
        try {
            while (this.hasBitesLeft()) {
                leftFork.acquire();
                rightFork.acquire();
                System.out.println(String.format("Philosopher %d is eating", this.philosopherId));
                leftFork.release();
                rightFork.release();
                this.numberOfBitesLeft--;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
