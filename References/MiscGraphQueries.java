package References;

import java.util.ArrayList;
import java.util.List;

public class MiscGraphQueries {
	static List<List<Integer>> adjList = new ArrayList<>();
	
	
	// Determine if there are any cycles in a directed graph
	static boolean isCyclic(int V) { 
		boolean[] visited = new boolean[V]; 
		boolean[] recStack = new boolean[V]; 
		for (int i = 0; i < V; i++) 
			if (isCyclicUtil(i, visited, recStack)) 
				return true; 
		return false; 
	}
	static boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack) { 
		if (recStack[i]) 
			return true;
		if (visited[i]) 
			return false; 
		visited[i] = true; 
		recStack[i] = true; 		
		for (int c: adjList.get(i)) 
			if (isCyclicUtil(c, visited, recStack)) 
				return true; 
		recStack[i] = false; 
		return false; 
	} 
	
	
	/* Query for ancestor/descendant relationship */
	
	// Pre processing
	static int[] timeIn;
	static int[] timeOut;
	
	static int count = 0;
	public static void dfs(int node, int par) {
		count++;
		timeIn[node] = count;
		for(int each: adjList.get(node)) {
			if(each != par)
				dfs(each, node);
		}
		count++;
		timeOut[node] = count;
	}
	
	// Actual Query
	public static boolean isAncestor(int u, int v) { 
		return (timeIn[u] <= timeIn[v] && timeOut[v] <= timeOut[u]);
	} 
}
