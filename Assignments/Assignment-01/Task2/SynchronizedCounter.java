package Task2;
public class SynchronizedCounter{
    long counter;

    public synchronized long increaseAndGet(){
       this.counter++;
       return this.counter;
    }
}