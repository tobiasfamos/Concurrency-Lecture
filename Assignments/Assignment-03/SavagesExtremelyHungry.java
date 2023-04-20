public class SavagesExtremelyHungry implements Runnable {

    private final Pot pot;
    private int savageId;
    private int numberOfPortionsTillFull;
    private int eatenPortions;

    public SavagesExtremelyHungry(int savageID, Pot pot, int numberOfPortionsTillFull) {
        this.savageId = savageID;
        this.pot = pot;
        this.numberOfPortionsTillFull = numberOfPortionsTillFull;
        this.eatenPortions = 0;
    }

    private void wait(int ms) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < ms) {
            continue;
        }
    }

    private Boolean hasEatenEnough(){
        return this.eatenPortions >= this.numberOfPortionsTillFull;
    }

    @Override
    public void run() {
        Boolean hasEaten;
        int currentTimeout = 100;
        while (!this.hasEatenEnough()) {
            hasEaten = pot.scoop(this.savageId);
            if(hasEaten){
                this.eatenPortions++;
                currentTimeout = 100;
            }else{
                currentTimeout = currentTimeout/2;
            }
            this.wait(currentTimeout);
        }
        System.out.println(String.format("Savage %d is full", this.savageId));
    }
}
