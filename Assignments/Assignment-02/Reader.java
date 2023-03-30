class Reader implements Runnable{
    private int numberOfReads;
    private int id;
    private Resource resource;
    private ReadWriteLock lock;

    public Reader(int numberOfReads, ReadWriteLock lock, Resource resource, int id){
        this.numberOfReads = numberOfReads;
        this.resource = resource;
        this.id = id;
        this.lock = lock;
    }
    @Override
    public void run() {
        int currentRead = 0;
        while (currentRead < numberOfReads){
            try {
                this.lock.lockRead();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Reader " + id + " reads: " + resource.read());
            this.lock.unlockRead();
            currentRead += 1;
        }
    }
}