package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF grid; // A disjoint set of the members of the grid of the simulation
    private boolean[] openSites;
    private int top;
    private int bottom;
    private int numberOpen; // Keeps track of the number of open sites
    private int dimension; // Stores the number of elements in the row of the system

    /* Converts a 2D coordinate into an integer */
    private int convert(int y, int x) {
        return y * dimension + x + dimension;
    }

    /* Checks if entered coordinates are valid */
    private void validate(int x, int y) {
        if (x < 0 || y < 0) {
            throw new java.lang.IndexOutOfBoundsException("coordinates out of bounds");
        } else if (x >= dimension || y >= dimension) {
            throw new java.lang.IndexOutOfBoundsException("coordinates out of bounds");
        }
    }

    /* Creates the virtual top and bottom */
    private void createVirtual() {
        top = 0;
        openSites[top] = true;
        for (int i = top; i < dimension - 1; i++) {
            grid.union(i, i + 1);
            openSites[i + 1] = true;
        }
        bottom = dimension * dimension + dimension;
        openSites[bottom] = true;
        for (int i = bottom; i < bottom + (dimension - 1); i++) {
            grid.union(i, i + 1);
            openSites[i + 1] = true;
        }
    }

    /* create N-by-N grid, with all sites initially blocked and a virtual top and bottom */
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("N should be greater than 0");
        }
        int virtual = N + 2;
        grid = new WeightedQuickUnionUF(virtual * N);
        openSites = new boolean[virtual * N];
        numberOpen = 0;
        dimension = N;
        createVirtual();
    }

    private void checkNeighbor(int row, int col) {
        int i = convert(row, col);
        int up = i - dimension;
        int down = i + dimension;
        int left = i - 1;
        int right = i + 1;

        if (openSites[up]) {
            grid.union(i, up);
        }
        if (openSites[down]) {
            grid.union(i, down);
        }
        if (openSites[left]) {
            grid.union(i, left);
        }
        if (openSites[right]) {
            grid.union(i, right);
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        int i = convert(row, col);
        if (openSites[i]) {
            return;
        }
        openSites[i] = true;
        numberOpen += 1;
        checkNeighbor(row, col);
    }

    /* Checks if a site is open */
    public boolean isOpen(int row, int col) {
        validate(row, col);
        int i = convert(row, col);
        return openSites[i];
    }

    /* Checks if site is full */
    public boolean isFull(int row, int col) {
        validate(row, col);
        int i = convert(row, col);
        return grid.find(i) == top;
    }

    /* returns the number of open sites */
    public int numberOfOpenSites() {
        return numberOpen;
    }

    /* returns if the system percolates or not */
    public boolean percolates() {
        return grid.find(bottom) == top;
    }

    // tests
    public static void main(String[] args) {
        Percolation sim = new Percolation(5);
        sim.open(0, 1);
        sim.open(1, 1);
        sim.open(2, 1);
        sim.open(3, 1);
        sim.open(4, 1);
        if (sim.isFull(3, 1)) {
            System.out.println("is full");
        }
        if (sim.percolates()) {
            System.out.println("yes");
        }
    }
}
