public class ConsensusRunner {
    public static void main(String[] args) throws InterruptedException {
        Consensus c = new Consensus();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> {
                Object output = c.decide(finalI);
                System.out.println(output);
            });
            threads[i].start();
        }
        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }
    }
}
