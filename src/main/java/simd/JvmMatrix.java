package simd;

public class JvmMatrix {

    private final double[][] data;

    public JvmMatrix(double[][] data) {
        this.data = data;
    }

    /**
     * Matrix multiplication.
     */
    public JvmMatrix mul(JvmMatrix that) {
        int rowsA = this.data.length;
        int colsA = this.data[0].length;
        int colsB = that.data[0].length;
        double[][] result = new double[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += this.data[i][k] * that.data[k][j];
                }
            }
        }
        return new JvmMatrix(result);
    }

}
