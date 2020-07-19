package References;
import java.util.*;

public class DisjointSet {
	
	public static class disjoint {
		int[] rank;
		int[] parent;
		int[] size;
		
		public disjoint(int N) {
			rank = new int[N];
			parent = new int[N];
			size = new int[N];
			for(int i = 0; i < parent.length; i++) {
				parent[i] = i;
				size[i] = 1;
			}
		}
		
		public int findRep(int i) { // finds the "representative" of this set
			if(parent[i] == i) {
				return i;
			}
			int n = findRep(parent[i]);
			parent[i] = n; //optional: path compression to reduce further queries to O(1)
			return n;
		}
		
		public void union(int i, int j) { // union by rank to maximize efficiency
			int x = findRep(i), y = findRep(j);
			if(x == y) {
				return; // already in same group
			}
			if(rank[x] > rank[y]) {
				parent[y] = x;
				size[x] += size[y];
			} else if(rank[y] > rank[x]) {
				parent[x] = y;
				size[y] += size[x];
			} else {
				parent[x] = y;
				rank[y]++;
				size[y] += size[x];
			}
		}
		
		// Example queries. Modify as needed
		public int size(int node) {
			return size[findRep(node)];
		}
		
		public boolean sameGroup(int n1, int n2) {
			return findRep(n1) == findRep(n2);
		}
		
	}
}
