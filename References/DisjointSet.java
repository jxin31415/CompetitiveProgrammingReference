package References;
import java.util.*;

public class DisjointSet {
	static int[] rank;
	static int[] parent;
	static int[] size; // if you need size of a group, query size[findRep(i)]

	public static void main(String[] args) {
		//Set-up
		rank = new int[10];
		parent = new int[10];
		size = new int[10];
		for(int i = 0; i < parent.length; i++) {
			parent[i] = i;
			size[i] = 1;
		}
		
		// Client code below
	}
	
	public static int findRep(int i) { // finds the "representative" of this set
		if(parent[i] == i) {
			return i;
		}
		int n = findRep(parent[i]);
		parent[i] = n; //optional: path compression to reduce further queries to O(1)
		return n;
	}
	
	public static void union(int i, int j) { // union by rank to maximize efficiency
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
}
