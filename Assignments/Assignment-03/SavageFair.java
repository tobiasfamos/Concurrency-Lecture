/*
This is a little unconventional but i found this approach to work pretty well.
I have impelmented an inverse exponential wait. This means, at each point, a Savage has to wait for the pot to be
refilled, thus has the chance of suffering starvation, the waiting time is reduced by half. And when the savage can
finally eat, the wait time is reset.
This ensures that eventually each savage will be able to eat (as when he is starved, his waiting time will shrink to such
a small number, he will constantly ask for the pot until he gets it.
 */
public class SavageFair {

    public static void main(String[] args) throws InterruptedException {
        int potCapacity = 5;
        int savagesCount = 10;
        Cook cook = new Cook();
        Pot pot = new Pot(potCapacity, cook);

        Thread[] savages = new Thread[savagesCount];

        for (int i = 0; i < savagesCount; i++) {
            savages[i] = new Thread(new SavagesExtremelyHungry(i, pot, 20));
        }
        Thread cookThread = new Thread(cook);
        cookThread.start();
        for (int i = 0; i < savagesCount; i++) {
            savages[i].start();
        }
        try {
            for (Thread savage : savages) {
                savage.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cook.signalMealOver();
        cookThread.join();
    }
}
