package lab11.graphs;

import java.util.Stack;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;        // source
    private int t;        // target
    private boolean cycleChecked = false;
    private Maze maze;    // maze
    private int[] parent;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        parent = new int[maze.V()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        s = 0;
        t = maze.xyTo1D(maze.N(), maze.N());
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        for (int i = 0; i < maze.V(); i++) {
            if (!marked[i] && !cycleChecked)
                dfs(i);
        }
    }

    // Helper methods go here
    private void dfs(int v) {
        marked[v] = true;
        announce();

        Stack<Integer> s = new Stack<>();
        s.push(v);
        parent[v] = -1;

        while (!s.empty()) {
            int n = s.pop();
            for (int w : maze.adj(n)) {
                if (!marked[w]) {
                    marked[w] = true;
//                    edgeTo[w] = n;
                    distTo[w] = distTo[n] + 1;
                    parent[w] = n;
                    s.push(w);
                    announce();
                } else {
                    // WHO is parent of whom?
                    // n is the location of cycleChecked
                    if (parent[n] != w) {
                        cycleChecked = true;
                    }
                }
                if (cycleChecked) {
                    int vertex = n;
                    while (vertex != w) {
                        edgeTo[vertex] = parent[vertex];
                        vertex = parent[vertex];
                    }
                    return;
                }
            }
        }
    }
}

