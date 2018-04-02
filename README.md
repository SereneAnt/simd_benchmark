# simd_benchmark

Benchmarking SIMD-optimized matrix calculations with [Nd4j](https://github.com/deeplearning4j/nd4j) library.

## Prerequisites
* JDK 1.8
* Maven 3.x

## Run benchmarks
```bash
mvn verify
```

## Results
```
MacBook Pro (13-inch, 2016, Two Thunderbolt 3 ports)
CPU 2.4 GHz Intel Core i7
RAM 16 GB 1867 MHz LPDDR3

Benchmark                           Mode  Cnt        Score        Error  Units
MatrixTest.benchmark_ForLoop_3x03   avgt    5        0.246 ±      0.013  us/op
MatrixTest.benchmark_ForLoop_3x10   avgt    5        0.405 ±      0.020  us/op
MatrixTest.benchmark_ForLoop_3x100  avgt    5        2.635 ±      0.390  us/op
MatrixTest.benchmark_ForLoop_Huge   avgt    5  6056577.701 ± 250555.170  us/op
MatrixTest.benchmark_Nd4j_3x03      avgt    5        5.298 ±      0.518  us/op
MatrixTest.benchmark_Nd4j_3x10      avgt    5        5.229 ±      0.320  us/op
MatrixTest.benchmark_Nd4j_3x100     avgt    5        7.010 ±      0.450  us/op
MatrixTest.benchmark_Nd4j_Huge      avgt    5    24734.196 ±   9854.021  us/op
```
