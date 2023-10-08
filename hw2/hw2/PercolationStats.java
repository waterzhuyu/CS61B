package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private final double mean, stddev, confidenceLow, confidenceHigh;
    /**
     * Perform T independent experiments on an N-by-N grid.
     * @param N the side length of grid.
     * @param T times of experiments
     * @param pf Percolation Factory
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Arg N and T should greater than 0.\n");
        }
        double[] xt = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                p.open(StdRandom.uniform(N), StdRandom.uniform(N));
            }
            xt[i] = (double) p.numberOfOpenSites() / N * N;
        }
        this.mean = StdStats.mean(xt);
        this.stddev = StdStats.stddev(xt);
        this.confidenceLow = this.mean - 1.96 * this.stddev / Math.sqrt(T);
        this.confidenceHigh = this.mean + 1.96 * this.stddev / Math.sqrt(T);
    }

    /**
     * sample mean of percolation threshold
     * @return mean
     */
    public double mean() {
        return this.mean;
    }

    /**
     * sample standard deviation of percolation threshold
     * @return stddev
     */
    public double stddev() {
        return this.stddev;
    }

    /**
     * low endpoint of 95% confidence interval
     * @return confidenceLow
     */
    public double confidenceLow() {
        return this.confidenceLow;
    }

    /**
     * high endpoint of 95% confidence interval
     * @return confidenceHigh
     */
    public double confidenceHigh() {
        return this.confidenceHigh;
    }
}
