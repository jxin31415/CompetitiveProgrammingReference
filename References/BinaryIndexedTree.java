package References;

public class BinaryIndexedTree { // also known as a Fenwick tree

	public static class BITree {
		int N;
		int[] tree;
		
		public BITree(int n) {
			this.N = n;
			tree = new int[n];
		}
		
		public BITree(int[] arr) {
			N = arr.length;
			tree = new int[arr.length];
			for(int i = 0; i < arr.length; i++) {
				add(i, arr[i]);
			}
		}
		
		public void add(int idx, int dlta) {
			for (; idx < N; idx = idx | (idx + 1))
	            tree[idx] += dlta;
		}
		
		public int sum(int r) { // sum query (from 0 to r, inclusive). Can be modified to perform minimum, maximum, XOR query
	        int ret = 0;
	        for (; r >= 0; r = (r & (r + 1)) - 1)
	            ret += tree[r];
	        return ret;
	    }

	    public int sum(int l, int r) { // range sum query, L, R inclusive. Can be modified to perform XOR query. CANNOT be modified to perform min, max query
	        return sum(r) - sum(l - 1);
	    }
	}
	
	static class FenwickTree2D {
		int[][] tree;
	    int n, m;

	    public FenwickTree2D(int N, int M) {
	    	n = N; m = M;
	    	tree = new int[n][m];
	    }

	    int sum(int x, int y) {
	        int ret = 0;
	        for (int i = x; i >= 0; i = (i & (i + 1)) - 1)
	            for (int j = y; j >= 0; j = (j & (j + 1)) - 1)
	                ret += tree[i][j];
	        return ret;
	    }
	    
	    // untested
	    int sum(int x1, int y1, int x2, int y2) {
	    	return sum(x2, y2) - sum(x1, y2) - sum(x2, y1) + sum(x1, y1);
	    }

	    void add(int x, int y, int delta) {
	        for (int i = x; i < n; i = i | (i + 1))
	            for (int j = y; j < m; j = j | (j + 1))
	                tree[i][j] += delta;
	    }
	}
	
}
