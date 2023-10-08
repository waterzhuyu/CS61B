package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private WeightedQuickUnionUF UF;  // add one virtual top site and one virtual bottom site
    private WeightedQuickUnionUF fullUF;
    // assume that N*N is top and N*N+1 is bottom
    private final int TOP, BOTTOM;
    private int openSites;
    private boolean[] sites;  // store if the site is open
    /**
     * Create N-by-N grid, with all sites initially blocked
     * @param N the grid size
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Argument N must greater than 0.\n");
        }
        this.N = N;
        this.TOP = N * N;
        this.BOTTOM = N * N + 1;
        this.sites = new boolean[N * N];
        this.openSites = 0;

        this.UF = new WeightedQuickUnionUF(N * N + 2);
        this.fullUF = new WeightedQuickUnionUF(N * N + 2);

        for (int i = 0; i < N; i++) {
            this.UF.union(BOTTOM, xyTo1D(N-1, i));
//            this.fullUF.union(TOP, xyTo1D(0, i));
        }
    }

    private void checkBounder(int row, int col) {
        if (row < 0 || row > this.N - 1) {
            throw new IndexOutOfBoundsException("Argument row over the range.\n");
        } else if (col < 0 || col > this.N - 1) {
            throw new IndexOutOfBoundsException("argument col over the range.\n");
        }
    }

    private int xyTo1D(int row, int col) {
        checkBounder(row, col);
        return row * this.N + col;
    }

    private int[] neighbors(int row, int col) {
        int[] neighbors = {-1, -1, -1, -1};
//        if (row == 0) {
//            neighbors[0] = xyTo1D(row, col);
//        }
        if (row > 0) {
            neighbors[0] = xyTo1D(row - 1, col);
        }
        if (row < this.N - 1) {
            neighbors[1] = xyTo1D(row + 1, col);
        }
        if (col > 0) {
            neighbors[2] = xyTo1D(row, col - 1);
        }
        if (col < this.N - 1) {
            neighbors[3] = xyTo1D(row, col + 1);
        }
        return neighbors;
    }

    /**
     * Open the site (row, col) if it is not open already
     * @param row x coordinate
     * @param col y coordinate
     */
    public void open(int row, int col) {
        checkBounder(row, col);
        if (isOpen(row, col))
            return;
        this.sites[xyTo1D(row, col)] = true;
        this.openSites += 1;

        // If (row, col) is in first line, then make it full.
        if (row == 0) {
            this.fullUF.union(TOP, xyTo1D(row, col));
        }

        for (int x : neighbors(row, col)) {
            if (x != -1 && sites[x]) {
                this.UF.union(xyTo1D(row, col), x);
                this.fullUF.union(xyTo1D(row, col), x);
                if (this.fullUF.connected(x, TOP)) {
                    this.UF.union(xyTo1D(row, col), TOP);
                    this.fullUF.union(xyTo1D(row, col), TOP);
                }
            }
        }

    }

    /**
     * Is the site (row, col) open?
     * @param row x coordinate
     * @param col y coordinate
     * @return open status
     */
    public boolean isOpen(int row, int col) {
        checkBounder(row, col);
        return this.sites[xyTo1D(row, col)];
    }

    /**
     * Is the site (row, col) full?
     * @param row x coordinate
     * @param col y coordinate
     * @return full status
     */
    public boolean isFull(int row, int col) {
        checkBounder(row, col);
        return this.fullUF.connected(TOP, xyTo1D(row, col));
    }

    /**
     * number of open sites
     * @return number of open sites
     */
    public int numberOfOpenSites() {
        return this.openSites;
    }

    /**
     * does the system percolate?
     * @return status of percolate
     */
    public boolean percolates() {
        return this.UF.connected(this.TOP, this.BOTTOM);
    }

    /**
     * use for unit testing.
     * @param args command line arguments
     */
    public static void main(String[] args) {

    }
}
