import java.io.*;
import java.util.*;
 
public class jimmy {
    static class Node {
        public Set<Integer> adjacent;

        public Node() {
            adjacent = new HashSet<>();
        }
    } 

    static int V;
    static Node[][] index;

    static boolean isCyclic(int source, int dest) {
        return index[source][dest] != null && index[dest][source] != null;
    }

    static void meld(int x, int j, int u, int v) {
        index[x][v] = new Node();
        index[x][u].adjacent.add(v);
        for (int w: index[j][v].adjacent) {
            if (index[x][w] == null) {
                meld(x, j, v, w);
            }
        }
    }

    static void add(int i, int j) {
        if (index[i][j] == null) {
            for (int x = 0; x < V; x++) {
                if (index[x][i] != null && index[x][j] == null) {
                    meld(x, j, i, j);
                }
            }
        }
    }

    public static void main(String[] args) {
        
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        int N = sc.nextInt(1, 1000);
        int M = sc.nextInt(0, 500000);
        int Q = sc.nextInt(1, 1000);

        V = N;
        index = new Node[V][V];
        for(int i = 0; i < V; i++) {
            index[i][i] = new Node();
        }

        long start = System.currentTimeMillis();

        while(M --> 0){
            int u = sc.nextInt(1, N);
            int v = sc.nextInt(1, N);
            u--; v--;

            // asserts(u != v);
            add(u, v);
            // asserts(!isCyclic(u, v));
        }

        long mid = System.currentTimeMillis();
        

        while(Q --> 0) {
            int u = sc.nextInt(1, N);
            int v = sc.nextInt(1, N);
            u--; v--;

            // asserts(u != v);
            add(u, v);
            
            if(isCyclic(u, v)) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }

        System.out.println(mid - start);
        System.out.println(System.currentTimeMillis() - mid);

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