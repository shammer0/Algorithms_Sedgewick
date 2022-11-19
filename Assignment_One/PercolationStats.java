
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private int trials;
	private int size;
	private double[] thresholds;
	
	public PercolationStats(int n, int trials) {
		if(n <= 0 || trials <= 0) {
			throw new IllegalArgumentException("Error");
		}
		this.trials = trials;
		this.size = n;
		this.thresholds = new double[trials];

		for(int i = 0; i < trials; i++) {
			Percolation percolation = new Percolation(size);
			while(!percolation.percolates()) {
				int row = StdRandom.uniformInt(1,size+1);
				int col = StdRandom.uniformInt(1,size+1);
				percolation.open(row,col);
			}
			thresholds[i] = (double) percolation.numberOfOpenSites() / (size * size);
		}
	}
	
	// sample mean of percolation threshold
    public double mean() {
		return StdStats.mean(thresholds);
	}

    // sample standard deviation of percolation threshold
    public double stddev() {
		return StdStats.stddev(thresholds);
	}

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
		return mean() - (1.96 * stddev()) / Math.sqrt(trials);
	}

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
		return mean() + (1.96 * stddev()) / Math.sqrt(trials);
	}

	// test client (see below)
	public static void main(String[] args) {
		PercolationStats percStats = 
		new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		// for(int i = 1; i <= percStats.size; i++) {
		// 	//for(int j = 1; j <= percStats.size; j++) {
		// 		percolation.open(6-i,1);
		// 		System.out.println(percolation.isOpen(6-i,1));
		// 		System.out.println(percolation.percolates());
		// 		System.out.println(percolation.numberOfOpenSites());
		// 		System.out.println(percolation.isFull(6-i,1));
		// 		System.out.println("--------- "+ (6-i) + ":" + 1 +" ----------");
		// 	//}
		// }

		// while(!percolation.percolates()) {
		// 	int row = StdRandom.uniformInt(1,percStats.size+1);
		// 	int col = StdRandom.uniformInt(1,percStats.size+1);
		// 	percolation.open(row,col);

		// 	char site = 'x';
		// 	for(int i = 1; i <= percStats.size; i++) {
		// 		for(int j = 1; j <= percStats.size; j++) {
		// 			if(percolation.isFull(i,j)) {
		// 				site = '*';
		// 			} else if(percolation.isOpen(i,j)) {
		// 				site = 'o';
		// 			} else {
		// 				site = 'x';
		// 			}
		// 			System.out.printf("%4c", site);
		// 		}
		// 		System.out.println("");
		// 	}
		// 	System.out.println("");
		// 	System.out.println("--------- "+ row + ":" + col +" ----------");
		// 	System.out.println("");
		// }

		

		// for(int i = 0; i < percStats.trials; i++) {
		// 	Percolation percolation = new Percolation(percStats.size);
		// 	while(!percolation.percolates()) {
		// 		int row = StdRandom.uniformInt(1,percStats.size+1);
		// 		int col = StdRandom.uniformInt(1,percStats.size+1);
		// 		percolation.open(row,col);
		// 	}
		// 	percStats.thresholds[i] = (double) percolation.numberOfOpenSites() / (percStats.size * percStats.size);
		// }
			
		StdOut.println("mean = " + percStats.mean());
		StdOut.println("stddev = " + percStats.stddev());
		StdOut.println("95% confidence interval = [" + percStats.confidenceLo() + ", " + percStats.confidenceHi() + "]");
	}
}