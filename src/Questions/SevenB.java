package Questions;/* ques 7
b)Write multithreaded web crawler.
 */


class MatrixMultiplicationTask implements Runnable {
    private final int[][] A;
    private final int[][] B;
    private final int[][] C;
    private final int i;
    private final int j;
    public MatrixMultiplicationTask(int[][] A, int[][] B, int[][] C, int i, int j) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.i = i;
        this.j = j;
    }
    @Override
    public void run() {
        for (int k = 0; k < B.length; k++) {
            C[i][j] += A[i][k] * B[k][j];
        }
    }
}