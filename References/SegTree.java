package References;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SegTree {
	static class segt {
        long[] t;
        int N;
        public segt(int n) {
            t = new long[4*n];
            N = n;
        }
        
        void build(long a[]) {
        	build(a, 1, 0, N-1);
        }
        
        void build(long a[], int v, int tl, int tr) {
            if (tl == tr) {
                t[v] = a[tl];
            } else {
                int tm = (tl + tr) / 2;
                build(a, v*2, tl, tm);
                build(a, v*2+1, tm+1, tr);
                t[v] = Math.min(t[v*2], t[v*2+1]);
            }
        }
        
        long queryMin(int l, int r) {
        	return queryMin(1, 0, N-1, l, r);
        }
 
        long queryMin(int v, int tl, int tr, int l, int r) {
            if (l > r)
                return Integer.MAX_VALUE/2;
            if (l == tl && r == tr)
                return t[v];
            int tm = (tl + tr) / 2;
            return Math.min(queryMin(v*2, tl, tm, l, Math.min(r, tm)),
                    queryMin(v*2+1, tm+1, tr, Math.max(l, tm+1), r));
        }
        
        void update(int pos, int new_val) {
        	update(1, 0, N-1, pos, new_val);
        }
 
        void update(int v, int tl, int tr, int pos, long new_val) {
            if (tl == tr) {
                t[v] = new_val;
            } else {
                int tm = (tl + tr) / 2;
                if (pos <= tm)
                    update(v*2, tl, tm, pos, new_val);
                else
                    update(v*2+1, tm+1, tr, pos, new_val);
                t[v] = Math.min(t[v*2], t[v*2+1]);
            }
        }
    }
	
	// Note: this does not work for sum queries, because you have to multiply the lazy propogation by the # of elements.
	// See below this class for addition on segments and sum query
	static class LazyProp { // lazy propogation, aka range updates. Addition on segments and max query
		
		int[] t;
		int[] lazy;
        int N;
        public LazyProp(int n) {
            t = new int[4*n];
            lazy = new int[4*n];
            N = n;
        }
        
        public void push(int v) {
        	// update children of v
        	t[v*2] += lazy[v];
        	lazy[v*2] += lazy[v];
        	t[v*2+1] += lazy[v];
        	lazy[v*2+1] += lazy[v];
        	
        	// reset v
        	lazy[v] = 0;
        }
        
        public void update(int L, int R, int add) {
        	update(1, 0, N-1, L, R, add);
        }
        
        public void update(int v, int tL, int tR, int L, int R, int add) {
        	if(L > R)
        		return;
        	if(L == tL && R == tR) {
        		t[v] += add;
        		lazy[v] += add;
        	} else {
        		push(v);
        		int tM = (tL + tR)/2;
        		update(v*2, tL, tM, L, Math.min(R, tM), add);
        		update(v*2+1, tM+1, tR, Math.max(L, tM+1), R, add);
        		t[v] = Math.max(t[v*2], t[v*2+1]);
        	}
        }
        
        public int query(int L, int R) {
        	return query(1, 0, N-1, L, R);
        }
        
        public int query(int v, int tL, int tR, int L, int R) {
        	if(L > R)
        		return Integer.MIN_VALUE;
        	if(L <= tL && tR <= R)
        		return t[v];
        	push(v);
        	int tM = (tL + tR) / 2;
        	return Math.max(query(v*2,  tL, tM, L, Math.min(R, tM)), query(v*2+1, tM+1, tR, Math.max(L, tM+1), R));
        }
		
	}
	
	static class LazyPropSum {
		
		int[] t;
		int[] lazy;
        int N;
        
        public LazyPropSum(int n) {
            t = new int[2*n];
            lazy = new int[2*n];
            N = n;
        }
        
        public void push(int v, int L, int R) {
        	// calculate sum of current node based on lazy
        	t[v] += (R - L + 1) * lazy[v];
        	
        	// propogate to children
        	if(L < R) {
        		lazy[2*v+1] += lazy[v];
        		lazy[2*v+2] += lazy[v];
        	}
        	
        	// reset this node
        	lazy[v] = 0;
        }
        
        public void update(int L, int R, int add) {
        	update(0, 1, N, L, R, add);
        }
        
        public void update(int v, int tL, int tR, int L, int R, int add) {
        	push(v, tL, tR);
        	if(L <= tL && tR <= R) {
        		lazy[v] += add;
        	} else {
        		int tM = (tL + tR) / 2;
        		if(L <= tM) {
        			update(2 * v + 1, tL, tM, L, R, add);
        		}
        		if(R >= tM + 1) {
        			update(2 * v + 2, tM + 1, tR, L, R, add);
        		}
        		
        		push(2 * v + 1, tL, tM);
        		push(2 * v + 2, tM + 1, tR);
        		t[v] = t[2 * v + 1] + t[2 * v + 2];
        	}
        }
        
        public int query(int L, int R) {
        	return query(0, 1, N, L, R);
        }
        
        public int query(int v, int tL, int tR, int L, int R) {
        	push(v, tL, tR);
        	if(L <= tL && tR <= R) {
        		return t[v];
        	}
        	int sum = 0;
        	int tM = (tL + tR) / 2;
        	if(L <= tM) {
        		sum += query(2 * v + 1, tL, tM, L, R);
        	}
        	if(R >= tM + 1) {
        		sum += query(2 * v + 2, tM+1, tR, L, R);
        	}
        	return sum;
        }
		
        public String toString() {
        	return Arrays.toString(t) + " " + Arrays.toString(lazy);
        }
	}
	
	static class LCA {
		int[] height, first, segtree;
	    boolean[] visited;
	    List<Integer> euler;
	    int n;
	    List<List<Integer>> adjList;
	    
	    public LCA(List<List<Integer>> adj) {
	    	int root = 1;
	        n = adj.size();
	        height = new int[n];
	        first = new int[n];
	        euler = new ArrayList<>();
	        visited = new boolean[n];
	        adjList = adj;
	        dfs(root, 0);
	        int m = euler.size();
	        segtree = new int[m * 4];
	        build(1, 0, m - 1);
	    }
	    
	    void dfs(int node, int h) {
	        visited[node] = true;
	        height[node] = h;
	        first[node] = euler.size();
	        euler.add(node);
	        for (int child : adjList.get(node)) {
	            if (!visited[child]) {
	                dfs(child, h + 1);
	                euler.add(node);
	            }
	        }
	    }
	    
	    void build(int node, int b, int e) {
	        if (b == e) {
	            segtree[node] = euler.get(b);
	        } else {
	            int mid = (b + e) / 2;
	            build(node << 1, b, mid);
	            build(node << 1 | 1, mid + 1, e);
	            int l = segtree[node << 1], r = segtree[node << 1 | 1];
	            segtree[node] = (height[l] < height[r]) ? l : r;
	        }
	    }
	    
	    int query(int node, int b, int e, int L, int R) {
	        if (b > R || e < L)
	            return -1;
	        if (b >= L && e <= R)
	            return segtree[node];
	        int mid = (b + e) >> 1;

	        int left = query(node << 1, b, mid, L, R);
	        int right = query(node << 1 | 1, mid + 1, e, L, R);
	        if (left == -1) return right;
	        if (right == -1) return left;
	        return height[left] < height[right] ? left : right;
	    }
	    
	    int lca(int u, int v) {
	        int left = first[u], right = first[v];
	        if (left > right) {
	        	int t = right;
	        	right = left;
	        	left = t;
	        }
	        return query(1, 0, euler.size() - 1, left, right);
	    }
	}
}
