import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedBuffer {
    int [] buffer;
    int current_position_to_consume;
    int current_position_to_produce;
    int buffer_size;
    ReentrantLock lock = new ReentrantLock();
    Condition bufferNotEmptyCondition = lock.newCondition();
    Condition bufferNotFullCondition = lock.newCondition();

    public SynchronizedBuffer(int size){
        this.buffer = new int[size];
        this.current_position_to_consume = 0;
        this.current_position_to_produce = 0;
        this.buffer_size = size;
       
        
    }
    
    public void produce() throws InterruptedException{
        try{
            this.lock.lock();
            while(this.is_buffer_full()){
                this.bufferNotFullCondition.await();
            }
            this.buffer[current_position_to_produce% this.buffer_size] = 1;
            this.current_position_to_produce += 1;
            this.bufferNotEmptyCondition.signalAll();
        }finally{
            this.lock.unlock();
        }
    }


    public void consume() throws InterruptedException{
        try{
            this.lock.lock();
            while(this.is_buffer_empty()){
                this.bufferNotEmptyCondition.await();
            }
            this.buffer[current_position_to_consume % this.buffer_size] = 0;
            this.current_position_to_consume += 1;
            this.bufferNotFullCondition.signalAll();
        }finally{
            this.lock.unlock();
        }
    }

    private boolean is_buffer_full() {
        return this.current_position_to_produce == this.current_position_to_consume + this.buffer_size;
    }
    
    private boolean is_buffer_empty() {
        return this.current_position_to_consume == this.current_position_to_produce;
    }
    
    public void printCurrentState(){
        System.out.println(Arrays.toString(this.buffer));
    }
}


