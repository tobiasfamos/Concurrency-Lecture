public class Pot {
    private int portionsAvailable;
    private int maxCapacity;
    private Cook cook;

    public Pot(int portionsAvailable, Cook cook) {
        this.portionsAvailable = portionsAvailable;
        this.maxCapacity = portionsAvailable;
        this.cook = cook;
    }


    synchronized public Boolean scoop(int savageId) {
        if(!this.cook.shouldRefill){
            if (this.hasPortionsLeft()) {
                System.out.println(String.format("Savage %d ate portion %d", savageId, this.portionsAvailable));
                this.portionsAvailable--;
                return true;
            }else{
                System.out.println(String.format("Savage %d asks for refill", savageId));
                this.cook.askForRefill(this);
                return false;
            }
        }
        return false;
    }

    public synchronized void refill(){
        this.portionsAvailable = maxCapacity;
        System.out.println(String.format("Cook Refilling pot to %d", this.maxCapacity));

    }

    public boolean hasPortionsLeft() {
        return this.portionsAvailable > 0;
    }
}
