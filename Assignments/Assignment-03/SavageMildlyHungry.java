public class SavageMildlyHungry implements Runnable {

    private final Pot pot;
    private int savageId;

    public SavageMildlyHungry(int savageID, Pot pot) {
        this.savageId = savageID;
        this.pot = pot;
    }

    private void wait(int ms) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < ms) {
            continue;
        }
    }

    @Override
    public void run() {
        Boolean hasEaten = false;
        while (!hasEaten) {
            hasEaten = pot.scoop(this.savageId);
            this.wait(100);
        }
    }
}
