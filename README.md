# simd_benchmark

Benchmarking SIMD-optimized matrix multiplication with a few libraries.

Note: the benchmarks are not comprehensive.
 - limited set of libraries used
 - limited set of matrix sizes tested

## Prerequisites
* JDK 1.8 - 21
* Maven 3.x

## Run benchmarks
```bash
mvn clean verify
```

## Results
```
MacBook Pro (16-inch, Nov 2024)
CPU Apple M4 Max
RAM 64 GB

OS: Macos Sequoia 15.3.2

openjdk version "21.0.2" 2024-01-16
OpenJDK Runtime Environment (build 21.0.2+13-58)
OpenJDK 64-Bit Server VM (build 21.0.2+13-58, mixed mode, sharing)

Benchmark                              Mode    Cnt    Score   Error  Units
MatrixTest.blas_600x384x602          sample    610   49.327 ± 0.140  ms/op
MatrixTest.blas_600x384x602:p0.00    sample          48.497          ms/op
MatrixTest.blas_600x384x602:p0.50    sample          49.152          ms/op
MatrixTest.blas_600x384x602:p0.90    sample          49.938          ms/op
MatrixTest.blas_600x384x602:p0.95    sample          50.332          ms/op
MatrixTest.blas_600x384x602:p0.99    sample          51.686          ms/op
MatrixTest.blas_600x384x602:p0.999   sample          63.963          ms/op
MatrixTest.blas_600x384x602:p0.9999  sample          63.963          ms/op
MatrixTest.blas_600x384x602:p1.00    sample          63.963          ms/op
MatrixTest.ejml_600x384x602          sample   7168    4.185 ± 0.014  ms/op
MatrixTest.ejml_600x384x602:p0.00    sample           3.457          ms/op
MatrixTest.ejml_600x384x602:p0.50    sample           4.104          ms/op
MatrixTest.ejml_600x384x602:p0.90    sample           4.563          ms/op
MatrixTest.ejml_600x384x602:p0.95    sample           4.833          ms/op
MatrixTest.ejml_600x384x602:p0.99    sample           5.297          ms/op
MatrixTest.ejml_600x384x602:p0.999   sample           7.939          ms/op
MatrixTest.ejml_600x384x602:p0.9999  sample          13.550          ms/op
MatrixTest.ejml_600x384x602:p1.00    sample          13.550          ms/op
MatrixTest.jama_600x384x602          sample    486   61.883 ± 0.244  ms/op
MatrixTest.jama_600x384x602:p0.00    sample          59.638          ms/op
MatrixTest.jama_600x384x602:p0.50    sample          61.800          ms/op
MatrixTest.jama_600x384x602:p0.90    sample          62.980          ms/op
MatrixTest.jama_600x384x602:p0.95    sample          63.373          ms/op
MatrixTest.jama_600x384x602:p0.99    sample          65.214          ms/op
MatrixTest.jama_600x384x602:p0.999   sample          79.561          ms/op
MatrixTest.jama_600x384x602:p0.9999  sample          79.561          ms/op
MatrixTest.jama_600x384x602:p1.00    sample          79.561          ms/op
MatrixTest.java_600x384x602          sample    327   92.303 ± 0.258  ms/op
MatrixTest.java_600x384x602:p0.00    sample          90.702          ms/op
MatrixTest.java_600x384x602:p0.50    sample          92.144          ms/op
MatrixTest.java_600x384x602:p0.90    sample          93.323          ms/op
MatrixTest.java_600x384x602:p0.95    sample          93.926          ms/op
MatrixTest.java_600x384x602:p0.99    sample          97.654          ms/op
MatrixTest.java_600x384x602:p0.999   sample         106.037          ms/op
MatrixTest.java_600x384x602:p0.9999  sample         106.037          ms/op
MatrixTest.java_600x384x602:p1.00    sample         106.037          ms/op
MatrixTest.nd4j_600x384x602          sample  10507    2.855 ± 0.014  ms/op
MatrixTest.nd4j_600x384x602:p0.00    sample           1.556          ms/op
MatrixTest.nd4j_600x384x602:p0.50    sample           2.884          ms/op
MatrixTest.nd4j_600x384x602:p0.90    sample           3.088          ms/op
MatrixTest.nd4j_600x384x602:p0.95    sample           3.224          ms/op
MatrixTest.nd4j_600x384x602:p0.99    sample           3.748          ms/op
MatrixTest.nd4j_600x384x602:p0.999   sample           6.118          ms/op
MatrixTest.nd4j_600x384x602:p0.9999  sample          19.524          ms/op
MatrixTest.nd4j_600x384x602:p1.00    sample          19.595          ms/op
```
