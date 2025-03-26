package simd;

import org.ejml.simple.SimpleMatrix;
import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import Jama.Matrix;

import java.util.SplittableRandom;
import java.util.concurrent.TimeUnit;

public class MatrixTest {

    @Test
    public void launchBenchmark() throws Exception {
        Options opt = new OptionsBuilder()
                .include(this.getClass().getName() + ".*")
                // Set the following options as needed
                .mode(Mode.SampleTime)
                .timeUnit(TimeUnit.MILLISECONDS)
                .warmupTime(TimeValue.seconds(10))
                .warmupIterations(3)
                .measurementTime(TimeValue.seconds(10))
                .measurementIterations(3)
                .threads(1)
                .forks(1)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .jvmArgs("-XX:+UseSuperWord") //it's on by default anyway
                //.addProfiler(WinPerfAsmProfiler.class)
                .build();

        new Runner(opt).run();
    }


    @State(Scope.Benchmark)
    public static class BenchmarkState {
        JvmMatrix a600x384, b384x602;
        INDArray ndA600x384, ndB384x602;
        double[] blasA600x384, blasB384x602;
        SimpleMatrix ejmlA600x384, ejmlB384x602;
        Jama.Matrix jamaA600x384, jamaB384x602;

        @Setup(Level.Trial)
        public void setup() {
            double[][] dataA600x384 = generateData(600, 384);
            double[][] dataB384x602 = generateData(384, 602);

            a600x384 = new JvmMatrix(dataA600x384);
            b384x602 = new JvmMatrix(dataB384x602);

            ndA600x384 = Nd4j.create(dataA600x384);
            ndB384x602 = Nd4j.create(dataB384x602);

            blasA600x384 = asBlas(dataA600x384);
            blasB384x602 = asBlas(dataB384x602);

            ejmlA600x384 = new SimpleMatrix(dataA600x384);
            ejmlB384x602 = new SimpleMatrix(dataB384x602);

            jamaA600x384 = new Matrix(dataA600x384);
            jamaB384x602 = new Matrix(dataB384x602);
        }

        private double[][] generateData(int sizeI, int sizeJ) {
            double[][] result = new double[sizeI][sizeJ];
            final SplittableRandom rnd = new SplittableRandom();
            for (int i = 0; i < sizeI; i++) {
                for (int j = 0; j < sizeJ; j++) {
                    result[i][j] = rnd.nextDouble();
                }
            }
            return result;
        }

        private double[] asBlas(double[][] in) {
            //column-major order!
            final int m = in.length;
            final int n = in[0].length;
            final double[] res = new double[n * m];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    res[j * m + i] = in[i][j];
                }
            }
            return res;
        }
    }

    @Benchmark
    public void java_600x384x602(BenchmarkState state, Blackhole bh) {
        bh.consume(
                state.a600x384.mul(state.b384x602)
        );
    }

    @Benchmark
    public void nd4j_600x384x602(BenchmarkState state, Blackhole bh) {
        bh.consume(
                state.ndA600x384.mmul(state.ndB384x602)
        );
    }

    @Benchmark
    public void blas_600x384x602(BenchmarkState state, Blackhole bh) {
        bh.consume(
                BlasHelper.blasMul(state.blasA600x384, state.blasB384x602,
                        600, 384, 602 //DEBT: Hardcode!
                )
        );
    }

    @Benchmark
    public void ejml_600x384x602(BenchmarkState state, Blackhole bh) {
        bh.consume(
                state.ejmlA600x384.mult(state.ejmlB384x602)
        );
    }

    @Benchmark
    public void jama_600x384x602(BenchmarkState state, Blackhole bh) {
        bh.consume(
                state.jamaA600x384.times(state.jamaB384x602)
        );
    }

}
