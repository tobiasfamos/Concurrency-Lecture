class Writer implements Runnable{
    private int numberOfWrites;
    private int id;
    private Resource resource;
    private ReadWriteLock lock;

    public Writer(int numberOfWrites, ReadWriteLock lock, Resource resource, int id){
        this.numberOfWrites = numberOfWrites;
        this.lock = lock;
        this.resource = resource;
        this.id = id;
    }
    @Override
    public void run() {
        int currentWrite = 0;
        while (currentWrite < numberOfWrites){
            try {
                this.lock.lockWrite();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.resource.write(this.resource.read()+1);
            System.out.println("Writer " + id + " wrote: " + this.resource.read());
            this.lock.unlockWrite();
            currentWrite += 1;
        }
    }
}