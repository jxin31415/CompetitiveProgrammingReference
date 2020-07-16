package References;
import java.util.*;

public class TopoSort {
	
	// GFG Topo Sort. Only requirement in output is that i(u) < i(v), if u -> v is a directed edge
	public static class Topo {
		int N;
		boolean[] visited;
		
		public Topo(int N) {
			this.N = N;
		}
		
		public List<Integer> topoSort(List<List<Integer>> adjList) {
			visited = new boolean[N];
			Stack<Integer> stack = new Stack<Integer>();
			for (int i = 0; i < N; i++)
	            if (visited[i] == false)
	                topoSortUtil(i, stack, adjList);
			
			List<Integer> res = new ArrayList<>();
			while (!stack.empty()) 
	            res.add(stack.pop());
			return res;
		}
		
		public void topoSortUtil(int node, Stack<Integer> stack, List<List<Integer>> adjList) {
			visited[node] = true;
			for(int each: adjList.get(node)) {
				if(!visited[each]) {
					topoSortUtil(each, stack, adjList);
				}
			}
			stack.push(node);
		}
	}
	
	// Rather messy custom topo sort when you need a specific order for output
	public static class TopoCustom {
		PriorityQueue<Node> queue;
		boolean[] visited;
		int[] numParents;
		int N;
		
		public TopoCustom(int n) {
			N = n;
		}
		
		public List<Integer> topoSort(List<List<Integer>> adjList) {
			queue = new PriorityQueue<Node>(new Node());
			visited = new boolean[N];
			numParents = new int[N];
			for(int i = 1; i < adjList.size(); i++) {
				for(int each: adjList.get(i)) {
					numParents[each]++;
				}
			}
			for(int i = 1; i < adjList.size(); i++) {
				queue.add(new Node(i, numParents[i]));
			}

			List<Integer> res = new ArrayList<>();
			while(queue.size() > 0) {
				Node n = queue.poll();
				if(!visited[n.node]) {
					visited[n.node]= true;
					res.add(n.node);
					for(int each: adjList.get(n.node)) {
						numParents[each]--;
						queue.add(new Node(each, numParents[each]));
					}
				}
			}
			return res;
		}
		
		public class Node implements Comparator<Node>{
			int node, numPar;
			
			public Node(int n, int nP) {
				node = n;
				numPar = nP;
			}

			public Node() {
			}

			@Override
			public int compare(Node o1, Node o2) {
				if(o1.numPar > o2.numPar)
					return 1;
				else if(o2.numPar > o1.numPar)
					return -1;
				return Integer.compare(o1.node, o2.node);
			}
			
			public String toString() {
				return " " + node;
			}
		}
	}
}
