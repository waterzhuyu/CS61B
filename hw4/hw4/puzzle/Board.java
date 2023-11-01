package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {

    private int[][] tiles;
    private int N;
    private static final int BLANK = 0;
    /**
     * Constructs a board from an N-by-N array of tiles where
     * tiles[i][j] = tile at row i, column j
     * @param tiles tile
     */
    public Board(int[][] tiles) {
        this.N = tiles.length;
        this.tiles = new int[N][N];
        for (int i = 0; i < tiles.length; i++) {
            System.arraycopy(tiles[i], 0, this.tiles[i],0,  tiles[i].length);
        }
    }

    /**
     * Returns value of tile at row i, column j (or 0 if blank).
     * @param i row number
     * @param j column number
     * @return value of tile
     */
    public int tileAt(int i, int j) {
        if (!(i >= 0 && i <= N - 1 && j >= 0 && j <= N - 1)) {
            throw new IndexOutOfBoundsException("i or j index out of bound");
        }
        return tiles[i][j];
    }

    /**
     * Returns the board size N
     * @return board size
     */
    public int size() {
        return N;
    }

    /**
     * {@source} joshh.ug/neighbors.html
     * @return
     */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ilillil = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ilillil[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int lllil = 0; lllil < hug; lllil++) {
            for (int lillill = 0; lillill < hug; lillill++) {
                if (Math.abs(-bug + lllil) + Math.abs(lillill - zug) - 1 == 0) {
                    ilillil[bug][zug] = ilillil[lllil][lillill];
                    ilillil[lllil][lillill] = BLANK;
                    Board neighbor = new Board(ilillil);
                    neighbors.enqueue(neighbor);
                    ilillil[lllil][lillill] = ilillil[bug][zug];
                    ilillil[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    /**
     * Hamming estimate described below
     * @return distance
     */
    public int hamming() {
        int dist = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) != BLANK && tileAt(i, j)!= N * i + j + 1) {
                    // the tile is not blank
                    dist++;
                }
            }
        }
        return dist;
    }

    /**
     * Manhattan estimate described below
     * @return distance
     */
    public int manhattan() {
        int dist = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int actual = tileAt(i, j);
                if (actual != BLANK) {
                    // tiles[i][j] / N is the expected row, and tiles[i][j] % N is the expected column number.
                    dist += Math.abs((actual - 1) / N - i) + Math.abs((actual - 1) % N - j);
                }
            }
        }
        return dist;
    }

    @Override
    public int estimatedDistanceToGoal() {
//        return hamming();
        return manhattan();
    }

    /**
     * Returns true if this board's tile values are the same
     * position as y's
     * @param y
     * @return
     */
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }

        if (y == null || y.getClass() != this.getClass()) {
            return false;
        }

        Board other = (Board) y;
        if (other.size() != N) {
            return false;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) != other.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }
}
