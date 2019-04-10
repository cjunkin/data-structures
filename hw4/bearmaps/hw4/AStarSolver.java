package bearmaps.hw4;
import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private DoubleMapPQ<Vertex> pq;
    private HashMap<Vertex, Double> disTo;

    private List<Vertex> solution;
    private double weight;
    private SolverOutcome outcome;
    private int states;

    private Stopwatch sw;
    private double time;
    private double timeOut;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        sw = new Stopwatch();
        pq = new DoubleMapPQ<>();
        disTo = new HashMap<>();
        solution = new ArrayList<>();
        timeOut = timeout;

        disTo.put(start, 0.0);
        solution.add(start);
        queue(input, start, end);
        time = sw.elapsedTime();
    }

    private void queue(AStarGraph<Vertex> input, Vertex start, Vertex end) {
        List<WeightedEdge<Vertex>> neighbors = input.neighbors(start);
        for (WeightedEdge<Vertex> edge : neighbors) {
            Vertex v = edge.to();
            double storedDis = disTo.get(start) + edge.weight();
            double dis = storedDis + input.estimatedDistanceToGoal(v, end);
            if (pq.contains(v) && storedDis < disTo.get(v)) {
                pq.changePriority(v, dis);
                disTo.put(v, storedDis);
            } else if (!pq.contains(v)){
                pq.add(v, dis);
                disTo.put(v, storedDis);
            }
        }
        relax(input, end);
    }

    private void relax(AStarGraph<Vertex> input, Vertex end) {
        Vertex v = pq.removeSmallest();
        states += 1;
        time = sw.elapsedTime();

        if (time > timeOut) {
            outcome = SolverOutcome.TIMEOUT;
            weight = 0;
            solution = null;
            return;
        } else if (v.equals(end)) {
            solution.add(v);
            weight = disTo.get(end);
            outcome = SolverOutcome.SOLVED;
            return;
        } else if (pq.size() == 0) {
            outcome = SolverOutcome.UNSOLVABLE;
            weight = 0;
            solution = null;
            return;
        }

        solution.add(v);
        queue(input, v, end);
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