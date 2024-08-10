import java.io.*;
import java.util.*;
 
public class jimmy {
    static long MOD = 1000000007;
    static List<Set<Integer>> adj = new ArrayList<>();
    static int[] counter;
    static int[] free_edges;
    public static void main(String[] args) {
        
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        int N = sc.nextInt(1, 100000);
        int T = sc.nextInt(1, 100000);

        for(int i = 0; i <= N; i++){
            adj.add(new HashSet<>());
        }

        for(int i = 1; i < N; i++) {
            int u = sc.nextInt(1, N);
            int v = sc.nextInt(1, N);

            asserts(u != v);
            asserts(!adj.get(u).contains(v));
            asserts(!adj.get(v).contains(u));

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        LCA lca = new LCA(adj, 1);

        counter = new int[N+1];
        free_edges = new int[N+1];

        Set<tup> set = new HashSet<>();
        while(T --> 0) {
            int a = sc.nextInt(1, N);
            int b = sc.nextInt(1, N);

            asserts(!set.contains(new tup(a, b)));
            set.add(new tup(a, b));

            int u = lca.lca(a, b);

            counter[a]++;
            counter[b]++;
            counter[u] -= 2;
        }

        // System.out.println(Arrays.toString(counter));

        dfs(1, 0);
        
        // System.out.println(Arrays.toString(free_edges));

        int count = 0;
        for(int each: free_edges)
            count += each;
        
        long ans = 1;
        while(count --> 0){
            ans *= 2;
            ans %= MOD;
        }

        out.println(ans);
        out.close();
    }

    public static int dfs(int node, int parent) {
        int count = 0;
        int sum = 0;

        for(int each: adj.get(node)) {
            if(each != parent) {
                int x = dfs(each, node);
                if(x == 0)
                    count++;
                sum += x;
            }
        }

        free_edges[node] = count;
        return sum + counter[node];
    }

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