import java.io.*;
import java.util.*;
 
public class jimmy_slow {
    static class Node {
        public Set<Integer> adjacent;
 
        public Node() {
            adjacent = new HashSet<>();
        }
    }
 
    static class Graph {
 
        public int V;
        public Node[][] index;
        public List<Set<Integer>> adj;
 
        public Graph(int V)
        {
            this.V = V;
 
            adj = new ArrayList<>();
            for(int i = 0; i < V; i++) {
                adj.add(new HashSet<>());
            }
 
            index = new Node[V][V];
        }
 
        public boolean isCyclic(int source, int dest) {
            return index[source][dest] != null && index[dest][source] != null;
        }
 
        public void meld(int x, int j, int u, int v) {
            index[x][v] = new Node();
            asserts(!index[x][u].adjacent.contains(v)); // My code is currently relying on this assumption
            index[x][u].adjacent.add(v);
            for (int w: index[j][v].adjacent) {
                if (index[x][w] == null) {
                    meld(x, j, v, w);
                }
            }
        }
 
        public void add(int i, int j) {
            if (index[i][j] == null) {
                for (int x = 0; x < V; x++) {
                    if (index[x][i] != null && index[x][j] == null) {
                        meld(x, j, i, j);
                    }
                }
            }
        }
 
        // Function to perform DFS and topological sorting
        void topologicalSortUtil(int v, boolean[] visited, Queue<Integer> stack) {
            visited[v] = true;
            for (int i : adj.get(v)) {
                if (!visited[i])
                    topologicalSortUtil(i, visited, stack);
            }
    
            stack.add(v);
        }
 
        void topologicalSort() {
            Queue<Integer> stack = new LinkedList<>();
            boolean[] visited = new boolean[V];
    
            for (int i = 0; i < V; i++) {
                if (!visited[i])
                    topologicalSortUtil(i, visited, stack);
            }
 
            List<Integer> topoSort = new ArrayList<>();
            while(stack.size() != 0) {
                topoSort.add(stack.poll());
            }
 
            List<Set<Integer>> required = new ArrayList<>();
            boolean[][] visited2d = new boolean[V][V];
            for(int i = 0; i < V; i++) {
                required.add(new HashSet<>());
            }
 
            for(int i = 0; i < V; i++) {
                for(int j = i-1; j >= 0; j--) {
                    int u = topoSort.get(i);
                    int v = topoSort.get(j);
                    if (!visited2d[u][v] && adj.get(u).contains(v)) {
                        dfs(v, u, adj, visited2d, required);
                    }
                }
            }
 
            adj = required;
            // System.out.println(required);
        }

        void dfs(int node, int parent, List<Set<Integer>> adj, boolean[][] visited, List<Set<Integer>> required) {
            visited[parent][node] = true;
            required.get(parent).add(node);
            for (int each: required.get(node)) {
                if (!visited[parent][each]) {
                    dfs(each, parent, adj, visited, required);
                }
            }
        }
 
 
        public void buildTree(int u, boolean[] visited, int root) {
            visited[u] = true;
            index[root][u] = new Node();
 
            for(int each: adj.get(u)) {
                if(!visited[each]) {
                    index[root][u].adjacent.add(each);
                    buildTree(each, visited, root);
                }
            }
        }
 
        public void buildTrees() {
            for(int i = 0; i < V; i++) {
                boolean[] visited = new boolean[V];
                buildTree(i, visited, i);
            }
        }
    }
 
    public static void main(String[] args) {
        
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
 
        int N = sc.nextInt(1, 1000);
        int M = sc.nextInt(0, 500000);
        int Q = sc.nextInt(1, 1000);
 
        Graph g = new Graph(N);
        while(M --> 0){
            int u = sc.nextInt(1, N);
            int v = sc.nextInt(1, N);
            u--; v--;
 
            asserts(u != v);
            g.adj.get(u).add(v);
        }
 
        g.topologicalSort();
        g.buildTrees();
 
        while(Q --> 0) {
            int u = sc.nextInt(1, N);
            int v = sc.nextInt(1, N);
            u--; v--;
 
            asserts(u != v);
            g.add(u, v);
            
            if(g.isCyclic(u, v)) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
 
        out.close();
    }
    
    static void asserts(boolean cond) {
        if(!cond) {
            throw new IllegalArgumentException();
        }
    }
 
    static void shuffle(tup[] a) {
		Random get = new Random();
		for (int i = 0; i < a.length; i++) {
			int r = get.nextInt(a.length);
			tup temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
	}
 
    static class tup implements Comparable<tup>, Comparator<tup>{
		int a; int b;
		tup(int a,int b){
			this.a=a;
			this.b=b;
		}
		public tup() {
		}
		@Override
		public int compareTo(tup o){
			return Integer.compare(a,o.a);
		}
		@Override
		public int compare(tup o1, tup o2) {
			return Integer.compare(o1.a,o2.a);
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
    
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;
 
        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
 
        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }
 
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt(int lo, int hi) {
            int a = Integer.parseInt(next());
            asserts(a >= lo && a <= hi);
            return a;
        }
 
        long nextLong(long lo, long hi) {
            long a = Long.parseLong(next());
            asserts(a >= lo && a <= hi);
            return a;
        }
 
        double nextDouble(double lo, double hi) {
            double a = Double.parseDouble(next());
            asserts(a >= lo && a <= hi);
            return a;
        }
 
        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
        
        int[] readArray(int size, int lo, int hi) {
            String[] s = nextLine().split(" ");
            asserts(s.length == size);
 
            int[] a = new int[size];
            for(int i = 0; i < size; i++) {
                a[i] = Integer.parseInt(s[i]);
                asserts(a[i] >= lo && a[i] <= hi);
            }
            return a;
        }
        
        long[] readLongArray(int size, long lo, long hi) {
            String[] s = nextLine().split(" ");
            asserts(s.length == size);
 
            long[] a = new long[size];
            for(int i = 0; i < size; i++) {
                a[i] = Long.parseLong(s[i]);
                asserts(a[i] >= lo && a[i] <= hi);
            }
            return a;
        }
    }
    
}
