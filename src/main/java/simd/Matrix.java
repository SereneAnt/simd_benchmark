package simd;

public class Matrix {

    private final double[][] data;

    public Matrix(double[][] data) {
        this.data = data;
    }

    // Calculates matrix dot product, taken from
    // https://introcs.cs.princeton.edu/java/22library/Matrix.java.html
    public Matrix dot(Matrix other) {
        int m1 = this.data.length;
        int n1 = this.data[0].length;
        int m2 = other.data.length;
        int n2 = other.data[0].length;
        if (n1 != m2) throw new RuntimeException("Illegal matrix dimensions.");

        double[][] c = new double[m1][n2];
        for (int i = 0; i < m1; i++)
            for (int j = 0; j < n2; j++)
                for (int k = 0; k < n1; k++)
                    c[i][j] += this.data[i][k] * other.data[k][j];

        return new Matrix(c);
    }
}
