// please be gentle.

public class PercolationStats {
    private int[] opened;
    private double[] percThreshold;
    private int N, T;
    
    public PercolationStats(int n, int t) {  
        // perform T independent computational experiments on an N-by-N grid
        // set these guys up throughout the class
        N = n; 
        T = t;
        
        // get all pissy
        if (N <= 0) throw new IllegalArgumentException("Gotta getta grid");
        if (T <= 0) throw new 
            IllegalArgumentException("If you don't wanna test get outta here!");
        
        // then let's decide how to share
        opened = new int[T];
        percThreshold = new double[T];
        
        int i;
        Percolation p;
        
        for (i = 0; i < T; i++) {
            p = new Percolation(N);
            opened[i] = 0;

            int row = 1;
            int col = 1;
            while (!p.percolates()) {
                row = StdRandom.uniform(N) + 1;
                col = StdRandom.uniform(N) + 1;
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                    opened[i]++;
                }
            }
            
            percThreshold[i] = (double) opened[i] / (N*N);
        }
    }
   
    public double mean()  {              // sample mean of percolation threshold
        return StdStats.mean(percThreshold);
    }
   
    public double stddev() { // sample standard deviation of percolation threshold
        return StdStats.stddev(percThreshold);
    }
    public double confidenceLo() { 
        // returns lower bound of the 95% confidence interval
        return (mean() - ((1.96 * stddev()) / Math.sqrt((double) T)));
    }
   
    public double confidenceHi() { 
        // returns upper bound of the 95% confidence interval
        return (mean() + ((1.96 * stddev()) / Math.sqrt((double) T)));
    }
   
    public static void main(String[] args) { // test client, described below
        if (args.length != 2) throw new 
            IllegalArgumentException("Need exactly two numeric arguments");
        int t = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        
        PercolationStats stats;
        stats = new PercolationStats(n, t);
        
        StdOut.printf("mean                    = %f\n", stats.mean());
        StdOut.printf("stddev                  = %.18f\n", stats.stddev());
        StdOut.printf("95 %% confidence interval = %.18f, %.18f\n",
                      stats.confidenceLo(), stats.confidenceHi());
    }
}