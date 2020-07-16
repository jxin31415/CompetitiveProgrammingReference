package References;
import java.util.*;

public class NetworkFlow {
	static int inf = Integer.MAX_VALUE;
	
	int n;
	int[][] capacity; // store RESIDUAL capacity, not total capacity
	List<List<Integer>> adjList; // stores adjacent nodes
	
	public NetworkFlow(int n, List<List<Integer>> adj, int[][] cap) {
		adjList = adj;
		this.n = n;
		capacity = cap;
	}
	
	public int findMaxFlow(int source, int sink) {
		int flow = 0;
		int[] parent = new int[n];
		int nooFlow = bfs(source, sink, parent);
		
		while(nooFlow > 0) {
			flow += nooFlow;
			int cur = sink;
			System.out.println(cur);
			while(cur != source) {
				int prev = parent[cur];
				capacity[prev][cur] -= nooFlow;
				capacity[cur][prev] += nooFlow;
				cur = prev;
				System.out.println(cur);
			}
			nooFlow = bfs(source, sink, parent);
			System.out.println("FLOW:");
			System.out.println(flow);
			//for(int[] each: capacity)
			//	System.out.println(Arrays.toString(each));
		}
		
		return flow;
	}
	
	public int bfs(int source, int sink, int[] parent) {
		Arrays.fill(parent, -1);
		parent[source] = -2;
		Queue<tup> q = new LinkedList<>();
		q.add(new tup(source, inf));
		
		while(!q.isEmpty()) {
			int cur = q.peek().a;
			int flow = q.peek().b;
			q.poll();
			
			for(int each: adjList.get(cur)) {
				if(parent[each] == -1 && capacity[cur][each] > 0) {
					parent[each] = cur;
					int nooFlow = Math.min(flow, capacity[cur][each]);
					if(each == sink)
						return nooFlow;
					q.add(new tup(each, nooFlow));
				}
			}
		}
		
		return 0;
	}
	
	static class tup implements Comparable<tup>, Comparator<tup>{
		int a, b;
		tup(int a,int b){
			this.a=a;
			this.b=b;
		}
		public tup() {
		}
		@Override
		public int compareTo(tup o){
			return Integer.compare(o.b,b);
		}
		@Override
		public int compare(tup o1, tup o2) {
			return Integer.compare(o1.b, o2.b);
		}
		
		@Override
	    public int hashCode() {
			return Objects.hash(a, b);
	    }
 
	    @Override
	    public boolean equals(Object obj) {
	    	if (this == obj)
                return true;
	    	if (obj == null)
                return false;
	    	if (getClass() != obj.getClass())
                return false;
	    	tup other = (tup) obj;
	    	return a==other.a && b==other.b;
	    }
	    
	    @Override
	    public String toString() {
	    	return a + " " + b;
	    }
	}
}