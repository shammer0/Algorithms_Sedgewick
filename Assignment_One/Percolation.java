
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private final int sideLength;
	private final int MIN;
	private final int MAX;
	private int count = 0;
	private int topSite;
	private int bottomSite;
	//private int[] size;
	//private int[] parent;
	private boolean[] status; // 0 : BLOCKED, 1 : OPEN
	private WeightedQuickUnionUF wqu;
	private WeightedQuickUnionUF wqu2;
	
	public Percolation(int n) {
		if (n <= 0)
			throw new IllegalArgumentException("Error");
		sideLength = n;
		MIN = 1;		
		MAX = n;
		wqu = new WeightedQuickUnionUF(n*n + 2);
		wqu2 = new WeightedQuickUnionUF(n*n + 1);
		//size = new int[n*n + 2];
		// Blocked is -1, Open is !-1, 
		// and Full is find(topSite) == find(node)
		//parent = new int[n*n + 2];
		status = new boolean[n*n + 2];
		
		// Virtual Sites
		topSite = n*n;
		bottomSite = n*n + 1;
		
		//size[topSite] = 1;
		//parent[topSite] = topSite;
		status[topSite] = true;
		//size[bottomSite] = 1;
		//parent[bottomSite] = bottomSite;
		status[bottomSite] = true;
	}
	
	public void open(int row, int col) {
		validate(row);
		validate(col);
		if(isOpen(row,col)){
			return;
		}

		int targetSite = convertFrom2d(row,col);
		// int top = grid[row-2][col-1];
		// int right = grid[row-1][col];
		// int bottom = grid[row][col-1];
		// int left = grid[row-1][col-2];
		
		status[targetSite] = true;
		//top
		if(row-1 == 0){
			wqu.union(targetSite, topSite);
			wqu2.union(targetSite, topSite);
		} else {
			if(isOpen(row-1,col)) {
				wqu.union(targetSite, convertFrom2d(row-1,col));
				wqu2.union(targetSite, convertFrom2d(row-1,col));
			}
		}
		//right
		if(col-1 != MAX-1 && isOpen(row,col+1)) {
			wqu.union(targetSite, convertFrom2d(row,col+1));
			wqu2.union(targetSite, convertFrom2d(row,col+1));
		}
		//left
		if(col-1 != 0 && isOpen(row,col-1)) {
			wqu.union(targetSite, convertFrom2d(row,col-1));
			wqu2.union(targetSite, convertFrom2d(row,col-1));
		}
		//bottom
		if(row-1 == MAX-1) {
				wqu.union(targetSite, bottomSite);
		} else {
			if(isOpen(row+1,col)) {
				wqu.union(targetSite, convertFrom2d(row+1,col));
				wqu2.union(targetSite, convertFrom2d(row+1,col));
			}
		}
		count++;
	}
	
	public boolean isOpen(int row, int col) {
		validate(row);
		validate(col);
		return status[convertFrom2d(row,col)];
	}
	
	public boolean isFull(int row, int col) {
		validate(row);
		validate(col);
		
		return wqu2.find(convertFrom2d(row,col)) == wqu2.find(topSite);
	}
	
	public int numberOfOpenSites() {
		return count;
	}
	
	public boolean percolates() {
		return wqu.find(bottomSite) == wqu.find(topSite); 
	}
	
	
	// private void union(int p, int q) {
	// 	int i = find(p);
	// 	int j = find(q);
	// 	if (i == j) return;
		
	// 	if(size[i] > size[j]) {
	// 		parent[j] = i;
	// 		size[i] += size[j];
	// 	} else {
	// 		parent[i] = parent[j];
	// 		size[j] += size[i];
	// 	}
	// }
	
	// private int find(int p) {
	// 	while(p != parent[p]) {
	// 		p = parent[p];
	// 	}
	// 	return p;
	// }
	
	private void validate(int i) {
		if(i < MIN || i > MAX)
			throw new IllegalArgumentException("Error bad input: " + i);
	}

	private int convertFrom2d(int row, int col) {
		return sideLength * (row -1) + (col - 1);
	}

}