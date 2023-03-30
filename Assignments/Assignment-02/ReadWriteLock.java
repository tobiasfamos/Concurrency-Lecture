public class ReadWriteLock {
    private boolean isWriterActive;
    private int currentReaders;

    public synchronized void lockRead() throws InterruptedException {
        while(isWriterActive){
            wait();
        }
        this.currentReaders += 1;
    }
    public synchronized void unlockRead(){
        currentReaders--;
        notifyAll();
    }
    public synchronized void lockWrite() throws InterruptedException {
        while (isWriterActive || currentReaders != 0){
            wait();
        }
        isWriterActive = true;
    }
    public synchronized void unlockWrite(){
        isWriterActive = false;
        notifyAll();
    }
}

