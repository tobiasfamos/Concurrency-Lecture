public class BenchmarkMatrix {
    /*
    Time taken: 138ms
    Time taken: 42021ms
     */
    public static void main(String[] args) {
        MatrixMultiplication[] cases = {new MatrixMultiplication(100), new MatrixMultiplication(1000)};
        for(MatrixMultiplication c :cases){
            long start = System.currentTimeMillis();
            try {
                c.mutiply();
            } catch (Exception e) {
                e.printStackTrace();
            }
            long end = System.currentTimeMillis();
            System.out.println("Time taken: " + (end - start) + "ms");
        }
    }
}
