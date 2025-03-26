package simd;

import org.netlib.blas.BLAS;

public class BlasHelper {

    private static final BLAS blas = BLAS.getInstance();

    public static double[] blasMul(
            double[] a, //[m][p]
            double[] b, //[p][n]
            int m, int p, int n
    ) {
        final double[] c = new double[m * n]; //column-major order!
        blas.dgemm(
                "N", "N",
                m, n, p,
                1.0, a, m,
                b, p, 0.0,
                c, m);
        return c;
    }

}
