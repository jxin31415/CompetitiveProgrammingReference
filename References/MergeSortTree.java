package References;

import java.util.Arrays;

public class MergeSortTree {
	static class mst { // Merge sort tree: for some queries [L, R, K], finds the number of elements less than or equal to K between L and R
        int[][] t;
        int N;
        
        public mst(int n) {
            t = new int[30][n]; // log_2 n x n. Round up to be safe
            N = n;
        }
        
        void build(int a[]) {
        	build(a, 0, N-1, 0);
        }
        
        void build(int a[], int tl, int tr, int depth) {
            if (tl == tr) {
                t[depth][tl] = a[tl];
            } else {
                int tm = (tl + tr) / 2;
                build(a, tl, tm, depth+1);
                build(a, tm+1, tr, depth+1);
                for(int i = tl, j = tm+1, k = tl; k <= tr; k++) {
                	if(j > tr)
                		t[depth][k] = t[depth+1][i++];
                	else if (i > tm)
                		t[depth][k] = t[depth+1][j++];
                	else if(t[depth+1][i] < t[depth+1][j])
                		t[depth][k] = t[depth+1][i++];
                	else
                		t[depth][k] = t[depth+1][j++];
                }
            }
        }
        
        long query(int l, int r, int k) {
        	return query(0, N-1, l, r, 0, k);
        }
 
        long query(int tl, int tr, int l, int r, int depth, int k) {
        	if (l > r)
        		return 0;
            if (l == tl && r == tr)
                return binarySearch(t[depth], l, r, k);
            int tm = (tl + tr) / 2;
            return query(tl, tm, l, Math.min(r, tm), depth+1, k) + query(tm+1, tr, Math.max(l, tm+1), r, depth+1, k);
        }
        
        long binarySearch(int[] arr, int l, int r, int k) { // arr is sorted. Returns # of elements between [L, R] that are >= k
        	int ans = l-1;
            int lo = l, hi = r;
            
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (arr[mid] <= k) {
                    lo = mid+1;
                    ans = mid;
                } else {
                	hi = mid-1;
                }
            }
         
            return ans - l + 1;
        }
        
        // No update method possible in less than O(N)
    }
	
	static class distinctElements { // query the number of distinct elements in range [L, R]
		int[] nextOccurrence; // store next index of this value
		mst m;
		int N;
		
		public distinctElements(int n) {
			nextOccurrence = new int[n];
			m = new mst(n);
			N=n;
		}
		
		void build(int[] arr) {
    		int[] temp = new int[N]; // needs to be as large as the largest element. Can also be substituted with a map as need be
    		
    		for(int j = arr.length-1; j >= 0; j--) {
    			if(temp[arr[j]] != 0)
    				nextOccurrence[j] = temp[arr[j]];
    			else
    				nextOccurrence[j] = Integer.MAX_VALUE;
    			temp[arr[j]] = j;
    		}
    		m.build(nextOccurrence);
		}
		
		long query(int l, int r) {
			return (r-l+1) - m.query(l, r, r); // a value counts towards the total if the next occurrence of the value is outside the range
		}
	}
}
