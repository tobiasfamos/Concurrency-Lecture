public class ReadWriteLockStarvationFree extends ReadWriteLock{
    // to make it starvation free we have to implement a counter for the writers to see if someone is waiting on
    // a write lock
    private boolean isWriterActive;
    private int currentReaders;
    private int writersWaiting;

    public ReadWriteLockStarvationFree(){
        this.currentReaders = 0;
        this.writersWaiting = 0;
    }
    public synchronized void lockRead() throws InterruptedException {
        while(this.isWriterActive || this.writersWaiting != 0 ){
            wait();
        }
        this.currentReaders += 1;
    }
    public synchronized void unlockRead(){
        currentReaders--;
        notifyAll();
    }
    public synchronized void lockWrite() throws InterruptedException {
        this.writersWaiting++;
        while (this.isWriterActive || currentReaders != 0){
            wait();
        }
        isWriterActive = true;
    }
    public synchronized void unlockWrite(){
        isWriterActive = false;
        this.writersWaiting--;
        notifyAll();
    }
}
