public class Pot {
    private int portionsAvailable;
    private int maxCapacity;

    public Pot(int portionsAvailable) {
        this.portionsAvailable = portionsAvailable;
        this.maxCapacity = portionsAvailable;
    }

    public void scoop() {
        if (this.hasPortionsLeft()) {
            this.portionsAvailable--;
        }
    }

    public void refill(){
        this.portionsAvailable = maxCapacity;
    }

    public boolean hasPortionsLeft() {
        return this.portionsAvailable > 0;
    }
}
