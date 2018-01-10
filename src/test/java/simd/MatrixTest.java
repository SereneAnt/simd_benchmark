package simd;

import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MatrixTest {

    @Test
    public void launchBenchmark() throws Exception {
        Options opt = new OptionsBuilder()
            .include(this.getClass().getName() + ".*")
            // Set the following options as needed
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.MICROSECONDS)
            .warmupTime(TimeValue.seconds(1))
            .warmupIterations(3)
            .measurementTime(TimeValue.seconds(1))
            .measurementIterations(5)
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
        Matrix a3x3;
        Matrix b3x3;
        Matrix b3x10;
        Matrix b3x100;
        Matrix aHuge;
        Matrix bHuge;

        INDArray ndA3x3;
        INDArray ndB3x3;
        INDArray ndB3x10;
        INDArray ndB3x100;
        INDArray ndAhuge;
        INDArray ndBhuge;

        @Setup(Level.Trial)
        public void setup() {
            double[][] dataA3x3 = generateData(3, 3);
            double[][] dataB3x3 = generateData(3, 3);
            double[][] dataB3x10 = generateData(3, 10);
            double[][] dataB3x100 = generateData(3, 100);
            double[][]dataAhuge = generateData(1000, 1000);
            double[][]dataBhuge = generateData(1000, 1000);

            a3x3 = new Matrix(dataA3x3);
            b3x3 = new Matrix(dataB3x3);
            b3x10 = new Matrix(dataB3x10);
            b3x100 = new Matrix(dataB3x100);
            aHuge = new Matrix(dataAhuge);
            bHuge = new Matrix(dataAhuge);

            ndA3x3 = Nd4j.create(dataA3x3);
            ndB3x3 = Nd4j.create(dataB3x3);
            ndB3x10 = Nd4j.create(dataB3x10);
            ndB3x100 = Nd4j.create(dataB3x100);
            ndAhuge = Nd4j.create(dataAhuge);
            ndBhuge = Nd4j.create(dataBhuge);
        }

        private double[][] generateData(int sizeI, int sizeJ) {
            double[][] result = new double[sizeI][sizeJ];
            Random rand = new Random();
            for (int i = 0; i < sizeI; i++) {
                for (int j = 0; j < sizeJ; j++) {
                    result[i][j] = rand.nextDouble();
                }
            }
            return result;
        }
    }

    @Benchmark
    public void benchmark_ForLoop_3x03(BenchmarkState state, Blackhole bh) {
        Matrix dotProduct = state.a3x3.dot(state.b3x3);
        bh.consume(dotProduct);
    }

    @Benchmark
    public void benchmark_ForLoop_3x10(BenchmarkState state, Blackhole bh) {
        Matrix dotProduct = state.a3x3.dot(state.b3x10);
        bh.consume(dotProduct);
    }

    @Benchmark
    public void benchmark_ForLoop_3x100(BenchmarkState state, Blackhole bh) {
        Matrix dotProduct = state.a3x3.dot(state.b3x100);
        bh.consume(dotProduct);
    }

    @Benchmark
    public void benchmark_ForLoop_Huge(BenchmarkState state, Blackhole bh) {
        Matrix dotProduct = state.aHuge.dot(state.bHuge);
        bh.consume(dotProduct);
    }

    @Benchmark
    public void benchmark_Nd4j_3x03(BenchmarkState state, Blackhole bh) {
        INDArray dotProduct = state.ndA3x3.mmul(state.ndB3x3);
        bh.consume(dotProduct);
    }

    @Benchmark
    public void benchmark_Nd4j_3x10(BenchmarkState state, Blackhole bh) {
        INDArray dotProduct = state.ndA3x3.mmul(state.ndB3x10);
        bh.consume(dotProduct);
    }

    @Benchmark
    public void benchmark_Nd4j_3x100(BenchmarkState state, Blackhole bh) {
        INDArray dotProduct = state.ndA3x3.mmul(state.ndB3x100);
        bh.consume(dotProduct);
    }

    @Benchmark
    public void benchmark_Nd4j_Huge(BenchmarkState state, Blackhole bh) {
        INDArray dotProduct = state.ndAhuge.mmul(state.ndBhuge);
        bh.consume(dotProduct);
    }

}


