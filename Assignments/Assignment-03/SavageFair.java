/*

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
