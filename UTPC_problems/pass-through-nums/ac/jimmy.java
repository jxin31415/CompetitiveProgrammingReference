import java.io.*;
import java.util.*;
 
public class jimmy {

    static List<List<Integer>> adj = new ArrayList<>();
    public static void main(String[] args) {
        
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        int N = sc.nextInt(1, 1000);

        long[] A = sc.readLongArray(N, 1, 1000000000000000000l);

        for(int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(i == j)
                    continue;
                asserts(A[i] != A[j]);
                if(A[i] % A[j] == 0) {
                    adj.get(j).add(i); // j is a factor of i -> j goes to i
                }
            }
        }

        // System.out.println(adj);

        out.println(LongestPathDAG.findLongestPath(adj) + 1);
        out.close();
    }
    

    public static class LongestPathDAG {

        private static int findLongestPath(List<List<Integer>> graph) {
            // Create a topological order of the DAG
            Stack<Integer> topologicalOrder = topologicalSort(graph);
            
            // System.out.println(topologicalOrder);
            // Initialize the longest path distances
            int[] longestPathDistances = new int[graph.size()];
            Arrays.fill(longestPathDistances, 0);

            // Iterate over the topological order
            while(!topologicalOrder.isEmpty()) {
                int node = topologicalOrder.pop();
                // Update the longest path distances for the node's neighbors
                for (int neighbor : graph.get(node)) {
                    longestPathDistances[neighbor] = Math.max(longestPathDistances[neighbor], longestPathDistances[node] + 1);
                }
            }

            // System.out.println(Arrays.toString(longestPathDistances));
            
            int max = 0;
            for(int each: longestPathDistances)
                max = Math.max(max, each);
            // Return the longest path distance
            return max;
        }

        private static Stack<Integer> topologicalSort(List<List<Integer>> graph) {
            // Create a stack to store the topological order
            Stack<Integer> stack = new Stack<>();

            // Create a set to store the visited nodes
            Set<Integer> visited = new HashSet<>();

            // Iterate over the nodes in the graph
            for (int node = 0; node < graph.size(); node++) {
                if (!visited.contains(node)) {
                    topologicalSortDFS(graph, node, visited, stack);
                }
            }

            // Return the topological order
            return stack;
        }

        private static void topologicalSortDFS(List<List<Integer>> graph, int node, Set<Integer> visited, Stack<Integer> stack) {
            // Mark the node as visited
            visited.add(node);

            // Iterate over the node's neighbors
            for (int neighbor : graph.get(node)) {
                if (!visited.contains(neighbor)) {
                    topologicalSortDFS(graph, neighbor, visited, stack);
                }
            }

            // Push the node onto the stack
            stack.push(node);
        }
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