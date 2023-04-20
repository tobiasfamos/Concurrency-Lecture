import java.util.concurrent.Semaphore;

public class PhilosophersProblem {
    public static void main(String[] args) throws InterruptedException {
        int NUMBER_OF_PHILOSOPHERS = 5;
        int NUMBER_OF_BITES_PER_PHILOSOPHER = 5;
        Semaphore[] forks = new Semaphore[NUMBER_OF_PHILOSOPHERS];
        Thread[] threads = new Thread[NUMBER_OF_PHILOSOPHERS];
        for (int idxForks = 0; idxForks < NUMBER_OF_PHILOSOPHERS; idxForks++) {
            forks[idxForks] = new Semaphore(1, true);
        }

        for (int idxPhil = 0; idxPhil < NUMBER_OF_PHILOSOPHERS; idxPhil++) {
            Philosopher philosopher = new Philosopher(idxPhil, NUMBER_OF_BITES_PER_PHILOSOPHER, forks[idxPhil],
                    forks[(idxPhil + 1) % NUMBER_OF_PHILOSOPHERS]);
            threads[idxPhil] = new Thread(philosopher);
            threads[idxPhil].start();
        }

        for (int idxThread = 0; idxThread < NUMBER_OF_PHILOSOPHERS; idxThread++) {
            threads[idxThread].join();
        }

    }
}
