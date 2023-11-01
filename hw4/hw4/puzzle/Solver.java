package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {
    private ArrayDeque<WorldState> solution;
    private int moves;
    private class SearchNode implements Comparable<SearchNode> {
        private WorldState state;
        private int initTo;        // the number of moves made to reach
        //                                    this world state from the initial state
        private SearchNode prev;   // the reference to the previous search node
        private int heuristicDist;

        public SearchNode(WorldState state, int initTo, SearchNode node) {
            this.state = state;
            this.initTo = initTo;
            this.prev = node;
            this.heuristicDist = state.estimatedDistanceToGoal();
        }

        @Override
        public int compareTo(SearchNode other) {
            return this.initTo + this.heuristicDist - other.initTo - other.heuristicDist;

        }
    }

    /**
     * Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.
     * @param initial initial WorldState
     */
    public Solver(WorldState initial) {
        MinPQ<SearchNode> fringe = new MinPQ<>();

        SearchNode initialNode = new SearchNode(initial, 0, null);
        SearchNode goalNode = initialNode;
        fringe.insert(initialNode);

        while (!fringe.isEmpty()) {
            SearchNode curr = fringe.delMin();
            if (curr.state.isGoal()) {
                goalNode = curr;
                moves = goalNode.initTo;
                break;
            }
            for (WorldState neighbor : curr.state.neighbors()) {
                if (curr.prev != null && neighbor.equals(curr.prev.state)) {
                    continue;
                }
                fringe.insert(new SearchNode(neighbor, curr.initTo + 1, curr));
            }
        }

        this.solution = new ArrayDeque<WorldState>();
        solution.addFirst(initialNode.state);
        while (goalNode.prev != null) {
            goalNode = goalNode.prev;
            solution.addFirst(goalNode.state);
        }
    }

    /**
     * Returns the minimum number of moves to solve the puzzle starting
     * at the initial WorldState.
     * @return minimum number of moves to solve the puzzle
     */
    public int moves() {
        return this.moves;
    }

    /**
     * Returns the sequence of WorldStates from the initial WorldState
     * to the solution.
     * @return the sequence.
     */
    public Iterable<WorldState> solution() {
        return this.solution;
    }
}

