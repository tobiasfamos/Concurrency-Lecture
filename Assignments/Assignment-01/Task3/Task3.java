import java.util.Date;

/*Consider the classical producer-consumer problem: a group of P producer threads and a group of C consumer threads share a bounded circular buffer of size N. If the buffer is not full,
* producers are allowed to add elements; if the buffer is not empty, consumers can consume elements. Write a program that can correctly coordinate the producers and consumers and 
* their depositing and retrieving activities. For simplicity we assume that we have the same number T of producers and consumer threads. T and N are program arguments.
*/
public class Task3 {
    
    public static Long produceAndConsume(int numberOfThreads, int bufferSize, int numberOperations) throws InterruptedException {
        Thread[] threads = new Thread[numberOfThreads];
        int threadsPerCategory = numberOfThreads/2;
        System.out.println(threadsPerCategory); 
        SynchronizedBuffer buffer = new SynchronizedBuffer(bufferSize);
        for (int i = 0; i < threadsPerCategory; i++) {
            threads[i] = new Thread(new ConsumerRunnable(numberOperations, buffer));
        }
        for (int i = threadsPerCategory; i < threads.length; i++) {
            threads[i] = new Thread(new ProducerRunnable(numberOperations, buffer));
        }

        long startMilliseconds = new Date().getTime();

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        long endMilliseconds = new Date().getTime();
        buffer.printCurrentState();
        return endMilliseconds - startMilliseconds;
    }

    public static void main(String[] args) throws InterruptedException {
        long executionTime = Task3.produceAndConsume(10,5,1000);
        System.out.println("Took: " + executionTime);
    }
}