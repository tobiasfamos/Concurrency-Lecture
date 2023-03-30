import java.util.LinkedList;
import java.util.Queue;

public class ReadWriteLockFiFo extends ReadWriteLock {
        private int readers;
        private int writers;
        private int writeRequests;
        private Thread owner;
        private int entrantCount;
        private Queue<Thread> writerQueue;
        private Queue<Thread> readerQueue;

        public ReadWriteLockFiFo() {
            writerQueue = new LinkedList<>();
            readerQueue = new LinkedList<>();
        }

        public synchronized void lockRead() throws InterruptedException {
            Thread currentThread = Thread.currentThread();
            readerQueue.add(currentThread);
            while (writers > 0 || (writerQueue.size() > 0 && writerQueue.peek() != currentThread)) {
                wait();
            }
            readerQueue.remove();
            readers++;
        }

        public synchronized void unlockRead() {
            readers--;
            notifyAll();
        }

        public synchronized void lockWrite() throws InterruptedException {
            Thread currentThread = Thread.currentThread();
            writerQueue.add(currentThread);
            writeRequests++;
            while (readers > 0 || writers > 0 || writerQueue.peek() != currentThread) {
                wait();
            }
            writerQueue.remove();
            writeRequests--;
            writers++;
            owner = currentThread;
            entrantCount++;
        }

        public synchronized void unlockWrite() {
            if (Thread.currentThread() != owner) {
                throw new IllegalMonitorStateException("Calling thread does not hold the write lock");
            }
            entrantCount--;
            if (entrantCount == 0) {
                owner = null;
                writers--;
                notifyAll();
            }
        }


}

