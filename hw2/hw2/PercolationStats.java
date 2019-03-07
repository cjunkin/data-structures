package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double percolated;
    private double experiments;
    private double[] data;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException("N and T should be greater than 0");
        }

        Percolation sim = pf.make(N);
        data = new double[T];
        experiments = T;

        for (int i = 0; i < T || sim.percolates(); i++) {
            sim.open(StdRandom.uniform(N), StdRandom.uniform(N));
            data[i] = i / T;
            T--;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(data);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(data);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return (mean() - 1.96 * stddev()) / Math.sqrt(experiments);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return (mean() + 1.96 * stddev()) / Math.sqrt(experiments);
    }
}
