package bearmaps.hw4;
import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private DoubleMapPQ<Vertex> pq = new DoubleMapPQ<>();
    private HashMap<Vertex, Double> disTo = new HashMap<>();
    private HashMap<Vertex, Vertex> edgeTo = new HashMap<>();
    private HashSet<Vertex> marked = new HashSet<>();

    private LinkedList<Vertex> solution = new LinkedList<>();
    private double weight;
    private SolverOutcome outcome;
    private int states;
    private double time;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        disTo.put(start, 0.0);
        edgeTo.put(start, null);
        marked.add(start);
        pq.add(start, 0.0);

        while (pq.size() > 0) {
            Vertex v = pq.removeSmallest();
            time = sw.elapsedTime();
            if (time > timeout) {
                outcome = SolverOutcome.TIMEOUT;
                weight = 0;
                solution.clear();
                return;
            } else if (v.equals(end)) {
                weight = disTo.get(end);
                while (edgeTo.get(v) != null) {
                    solution.addFirst(v);
                    v = edgeTo.get(v);
                }
                solution.addFirst(v);
                outcome = SolverOutcome.SOLVED;
                return;
            }

            relax(input, v, end);
        }

        outcome = SolverOutcome.UNSOLVABLE;
        weight = 0;
        solution.clear();
        time = sw.elapsedTime();
    }

    private void relax(AStarGraph<Vertex> input, Vertex start, Vertex end) {
        List<WeightedEdge<Vertex>> neighbors = input.neighbors(start);
        for (WeightedEdge<Vertex> edge : neighbors) {
            Vertex v = edge.to();
            double storedDis = disTo.get(edge.from()) + edge.weight();
            double priority = storedDis + input.estimatedDistanceToGoal(v, end);
            if (marked.contains(v) && storedDis < disTo.get(v)) {
                pq.changePriority(v, priority);
                disTo.put(v, storedDis);
                edgeTo.put(v, start);
            } else if (!marked.contains(v)) {
                pq.add(v, priority);
                disTo.put(v, storedDis);
                edgeTo.put(v, start);
                marked.add(v);
            }
        }
        states += 1;
    }

    public SolverOutcome outcome() {
        return outcome;
    }

    public List<Vertex> solution() {
        return solution;
    }

    public double solutionWeight() {
        return weight;
    }

    public int numStatesExplored() {
        return states;
    }

    public double explorationTime() {
        return time;
    }
}
