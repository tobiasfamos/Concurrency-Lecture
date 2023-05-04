import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MatrixMultiplication {
    private int N;
    public MatrixMultiplication(int N){
        this.N = N;
    }
    public void mutiply() throws Exception {
        int[][] A = generateRandomMatrix(N);
        int[][] B = generateRandomMatrix(N);
        int[][] C = new int[N][N];

        ExecutorService executorService = Executors.newFixedThreadPool(N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                final int row = i;
                final int col = j;
                Callable<Integer> task = new Callable<Integer>() {
                    public Integer call() throws Exception {
                        int sum = 0;
                        for (int k = 0; k < N; k++) {
                            sum += A[row][k] * B[k][col];
                        }
                        return sum;
                    }
                };
                Future<Integer> future = executorService.submit(task);
                C[row][col] = future.get();
            }
        }

        executorService.shutdown();
    }

    private static int[][] generateRandomMatrix(int N) {
        Random random = new Random();
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = random.nextInt(10);
            }
        }
        return matrix;
    }
}
