package References;
import java.util.*;

public class BinaryLiftingLCA {
    
	public static class LCA {
		int N, L;
		List<Set<Integer>> adjList;
		int timer;
		int[] tIn, tOut;
		int[][] up;
		
		public LCA(List<Set<Integer>> adj, int root) {
			N = adj.size();
			adjList = adj;
			tIn = new int[N];
			tOut = new int[N];
			timer = 0;
			L = (int)Math.ceil(Math.log(N) / Math.log(2));
			up = new int[N][L+1];
			dfs(root, root);
		}
		
		public void dfs(int node, int parent) {
			tIn[node] = timer++;
			up[node][0] = parent;
			for(int i = 1; i <= L; i++) {
				up[node][i] = up[up[node][i-1]][i-1];
			}
			
			for(int each: adjList.get(node)) {
				if(each != parent)
					dfs(each, node);
			}
			
			tOut[node] = timer++;
		}
		
		public boolean isAncestor(int u, int v) {
			return tIn[u] <= tIn[v] && tOut[u] >= tOut[v];
		}
		
		public int lca(int u, int v) {
			if(isAncestor(u, v))
				return u;
			if(isAncestor(v, u))
				return v;
			for(int i = L; i >= 0; i--) {
				if(!isAncestor(up[u][i], v))
					u = up[u][i];
			}
			return up[u][0];
		}
	}
}
