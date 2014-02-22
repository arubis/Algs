// This was a little ugly. Then I noticed that the input to the API is 1-indexed
// instead of 0-indexed. Now it's REALLY ugly.

public class Percolation {
    private WeightedQuickUnionUF uf;
    private int[][] grid;
    private boolean[][] openState;
    private int N;
        
    public Percolation(int size) { // create N-by-N grid, with all sites blocked
        N = size+1;
        uf = new WeightedQuickUnionUF(N*N);
        grid = new int[N][N];
        openState = new boolean[N][N];
        int i, j;
        
        int cnt = 0;
        for (i = 0; i < N; i++) {  // give each site a unique root in uf
            for (j = 0; j < N; j++) {
                grid[i][j] = cnt++;      // here's our unique root
                openState[i][j] = false; // each site starts closed
            }
        }        
    }
    
    public void open(int i, int j) {      // open site (row i, column j) if it 
                              // is not already -- appallingly we want 1<=i<=N
        
        if (!isOpen(i, j)) {
            
            openState[i][j] = true;       // first, open the site itself

            // now, connect to any adjacent open sites
            if (i < N-1 && isOpen(i+1, j)) { uf.union(grid[i+1][j], grid[i][j]); }
            if (i > 1 && isOpen(i-1, j)) { uf.union(grid[i-1][j], grid[i][j]); }
            if (j < N-1 && isOpen(i, j+1)) { uf.union(grid[i][j+1], grid[i][j]); }
            if (j > 1 && isOpen(i, j-1)) { uf.union(grid[i][j-1], grid[i][j]); }
        }
    }
    
    public boolean isOpen(int i, int j) { // is site (row i, column j) open?
        if (i <= 0 || i > N) throw 
            new IndexOutOfBoundsException("row index i out of bounds");
        if (j <= 0 || j > N) throw 
            new IndexOutOfBoundsException("col index j out of bounds");
        
        return openState[i][j];
    }
    
    public boolean isFull(int i, int j) { // is site (row i, column j) full?
        int counter;
        // site i, j is full if it is in union with site (i=0, j) for any j.
        if (i == 1) { return true; } // trivial case
        for (counter = 1; counter < N; counter++) {
            if (uf.connected(grid[1][counter], grid[i][j])) { return true; }
        } 
        return false;
    }
    
    public boolean percolates() {         // does the system percolate?
        int bottom;
        
        for (bottom = 1; bottom < N; bottom++) {
            if (isFull(N-1, bottom)) { return true; }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        int gridSize;
        int i, j;
        
        if (args.length > 0) {
            gridSize = Integer.parseInt(args[0]);
        } else {
            gridSize = 7;
        }
        
        Percolation something;
        something = new Percolation(gridSize);
        
        // connect a few nodes
        i = 3;
        j = 3;
        StdOut.printf("Say, is node [%d][%d] open? ", i, j);
        if (something.isOpen(i, j)) { StdOut.println("Yes!"); }
        else { 
            StdOut.println("No! So let's open it..."); 
            something.open(i, j);
            StdOut.printf("Let's try it again. Is it open now? ");
            if (something.isOpen(i, j)) { StdOut.println("Yes!"); }
            else { StdOut.println("No, something is wrong with my code."); }
        }
        j++;
        StdOut.printf("If we open adjacent node [%d][%d], "
                          + "then they should be connected.\n", i, j);
        StdOut.printf("With [%d][%d] (%d) still closed, "
                          + "is it connected to [%d][%d] (%d)? ",
                          i, j, something.grid[i][j], i, j-1, 
                          something.grid[i][j-1]);
        if (!something.uf.connected(something.grid[i][j],
                          something.grid[i][j-1])) { StdOut.println("No!"); }
        else { StdOut.println("Yes, so something is wrong with my code."); }
        
        StdOut.printf("Now opening node [%d][%d] (%d)...",
                      i, j, something.grid[i][j]);
        something.open(i, j);
        StdOut.println("done.");
        StdOut.printf("Now, is [%d][%d] (%d) connected to [%d][%d] (%d)? ",
                    i, j, something.grid[i][j], i, j-1, something.grid[i][j-1]);
        
        if (something.uf.connected(something.grid[i][j], something.grid[i][j-1]))
             { StdOut.println("Yes!"); }
        else { StdOut.println("No, something is wrong with my code."); }
        
        StdOut.println();
        StdOut.printf("Is site [%d][%d] full? (It's full if it's connected "
                          + "to the top row by open sites.) ", i, j);
        if (something.isFull(i, j)) 
             { StdOut.println("Yes! Something has gone terribly wrong!"); }
        else { StdOut.println("Nope."); }
        
        StdOut.println("So, let's issue a bunch of connects...");
        
        int count;
        for (count = 1; count < j; count++) {
            StdOut.printf("Site [%d, %d] (root %d)-- ",
                          count, j, something.uf.find(something.grid[count][j]));
            something.open(count, j);
        }
        StdOut.println();
        
        StdOut.printf("\nSo now, is site [%d, %d] (root %d) full? ",
                      i, j, something.uf.find(something.grid[i][j]));
        StdOut.println(something.isFull(i, j));
        
        StdOut.printf("What if we also open site [1, %d]? Is [%d, %d] full? ",
                      j, i, j);
        something.open(1, j);
        StdOut.println(something.isFull(i, j));
        
        // We say the system percolates if there exists 
        // a path from the top row to the bottom.
        StdOut.printf("The size of the grid's side is currently %d.\n",
                      (something.N-1));
        StdOut.print("Does the system percolate? ");
        StdOut.println(something.percolates());
        
        StdOut.printf("What if we connect manually to the bottom?\n");
        for (count = j; count < something.N; count++) {
            // StdOut.printf("Open [%d, %d] :: ", count, j);
            something.open(count, j);
        }
        
        StdOut.print("Does the system percolate? ");
        StdOut.println(something.percolates());
    }
}
