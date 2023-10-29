package lab11.graphs;

import java.util.ArrayDeque;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private ArrayDeque<Integer> fringe; // perform as a priority queue
    private static final int INF = Integer.MAX_VALUE;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        this.fringe = new ArrayDeque<>();
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        return Math.abs(maze.toX(v) - maze.toX(t)) + Math.abs(maze.toY(v) - maze.toY(t));
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        int min = INF;
        int res = fringe.peek();
        for (int w : fringe) {
            if (!marked[w]) {
                if (h(w) < min) {
                    min = h(w);
                    res = w;
                }
            }
        }
        return res;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        this.fringe.add(s);

        while (!fringe.isEmpty()) {
            s = findMinimumUnmarked();
            fringe.remove(s);
            marked[s] = true;
            announce();

            if (s == t) {
                targetFound = true;
            }

            if (targetFound) {
                return;
            }

            for (int neighbor : maze.adj(s)) {
                if (!marked[neighbor]) {
                    edgeTo[neighbor] = s;
                    distTo[neighbor] = distTo[s] + 1;
                    fringe.add(neighbor);
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

