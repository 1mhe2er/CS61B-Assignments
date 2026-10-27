import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.
    private WeightedQuickUnionUF fulluf;
    private WeightedQuickUnionUF percolationuf;
    private boolean[][] openvalue;
    private int size;
    private int opensitenumber;

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        if (N < 0) {
            throw new java.lang.IllegalArgumentException("Wrong size!");
        }
        fulluf = new WeightedQuickUnionUF(N * N + 2);
        percolationuf = new WeightedQuickUnionUF(N * N + 2);
        size = N;
        opensitenumber = 0;
        openvalue = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            percolationuf.union(0, i + 2);
            percolationuf.union(1, N * N + 1 - i);
            fulluf.union(0, i + 2);
        }
        for (boolean[] row : openvalue) {
            for (boolean col: row) {
                col = false;
            }
        }
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        if (row < 0 || row > size - 1 || col < 0 || col > size - 1) {
            throw new java.lang.IndexOutOfBoundsException("Wrong row/col index");
        }

        if (openvalue[row][col]) {
            return;
        }

        openvalue[row][col] = true;
        opensitenumber++;

        int uprow = row - 1;
        int downrow = row + 1;
        int leftcol = col - 1;
        int rightcol = col + 1;
        int location = xyTo1D(row, col);

        if (uprow > -1 && openvalue[uprow][col]) {
            fulluf.union(xyTo1D(uprow, col), location);
            percolationuf.union(xyTo1D(uprow, col), location);
        }
        if (downrow < size && openvalue[downrow][col]) {
            fulluf.union(xyTo1D(downrow, col), location);
            percolationuf.union(xyTo1D(downrow, col), location);
        }
        if (leftcol > -1 && openvalue[row][leftcol]) {
            fulluf.union(xyTo1D(row, leftcol), location);
            percolationuf.union(xyTo1D(row, leftcol), location);
        }
        if (rightcol < size && openvalue[row][rightcol]) {
            fulluf.union(xyTo1D(row, rightcol), location);
            percolationuf.union(xyTo1D(row, rightcol), location);
        }

        return;
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        if (openvalue[row][col]) {
            return true;
        }
        return false;
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        if (fulluf.connected(xyTo1D(row, col), 0) && isOpen(row, col)) {
            return true;
        }
        return false;
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return opensitenumber;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        if (percolationuf.connected(0, 1)) {
            return true;
        }
        return false;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.
    private int xyTo1D(int row, int col) {
        return row * size + col + 2;
    }
}
