// please be gentle.

public class PercolationStats {
    int[] opened;
    int total_opened;
    int N, T;
    
    public PercolationStats(int n, int t) {  // perform T independent computational experiments on an N-by-N grid
        // set these guys up throughout the class
        N = n; T = t;
        
        // get all pissy
        if ( N <= 0 ) throw new IllegalArgumentException("Gotta getta grid");
        if ( T <= 0 ) throw new IllegalArgumentException("If you don't wanna test get outta here!");
        
        // then let's decide how to share
        opened = new int[T];
        total_opened = 0;
        int i;
        Percolation p;
        
        for(i = 0; i < T; i++) {
            p = new Percolation(N);
            opened[i] = 0;

            int row = 1;
            int col = 1;
            while( !p.percolates() ) {
                row = StdRandom.uniform(N) + 1;
                col = StdRandom.uniform(N) + 1;
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                    opened[i]++; total_opened++;
                }
            }
        }
    }
   
    public double mean()  {                  // sample mean of percolation threshold
        double per_perc = (double) N * N;
        return StdStats.mean(opened)/(per_perc);
    }
   
    public double stddev() {                 // sample standard deviation of percolation threshold
        return 5.0;
    }
    public double confidenceLo() {           // returns lower bound of the 95% confidence interval
        return 5.0;
    }
   
    public double confidenceHi() {           // returns upper bound of the 95% confidence interval
        return 5.0;
    }
   
    public static void main(String[] args) { // test client, described below
        if (args.length != 2) throw new IllegalArgumentException("Need exactly two numeric arguments");
        int t = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        
        PercolationStats stats;
        stats = new PercolationStats(n, t);
        
        StdOut.printf("mean                    = %.17f\n", stats.mean());
        StdOut.printf("stddev                  = %f\n", stats.stddev());
        StdOut.printf("95 %% confidence interval = %f, %f\n",
                      stats.confidenceLo(), stats.confidenceHi());
    }
}