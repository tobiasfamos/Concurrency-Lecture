public class Cook implements Runnable {
    public volatile Boolean shouldRefill;
    Boolean isMealGoingOn;
    Pot pot;
    public Cook(){
        this.shouldRefill = false;
        this.isMealGoingOn = true;
    }

    public void askForRefill(Pot pot){
        this.pot = pot;
        this.shouldRefill = true;
        System.out.println(String.format("Cook ased to refill: %b", this.shouldRefill));
    }

    public void signalMealOver(){
        this.isMealGoingOn = false;
    }

    private void refill(){
        this.pot.refill();
        this.shouldRefill = false;
    }

    private void wait(int ms) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < ms) {
            continue;
        }
    }
    @Override
    public void run() {
        while(this.isMealGoingOn){
            if(this.shouldRefill){
                this.refill();
            }
            this.wait(50);
        }
    }
}
