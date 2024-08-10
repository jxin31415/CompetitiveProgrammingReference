package spaceport.ac;
import java.io.*;
import java.util.*;
 
public class jimmy {
    
    static List<List<Integer>> adj = new ArrayList<>();
    static PriorityQueue<tup> pq = new PriorityQueue<>(new tup());
    public static void main(String[] args) {
        
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        
        int[] in = sc.readArray(2, 1, 1000000000);
        int N = in[0]; asserts(N > 0 && N <= 100000);
        int K = in[1];

        long[] arr = sc.readLongArray(N, 1, 1000);
        for(int i = 0; i <= N; i++){
            adj.add(new ArrayList<>());
        }

        for(int i = 1; i < N; i++){
            in = sc.readArray(2, 1, N);
            int u = in[0];
            int v = in[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        dfs(1, -1, N, arr);

        asserts(pq.size() == N);
        
        long total = 0;
        while(pq.size() > 0){
            tup t = pq.poll();
            long sub = Math.min(K, arr[t.a - 1]);
            arr[t.a-1] -= sub;
            K -= sub;

            total += arr[t.a-1] * t.b;
        }

        out.println(total);

        out.close();
    }

    static long dfs(int node, int parent, int N, long[] costs) {
        long sum = 1;
        long cost = 0;
        for(int each: adj.get(node)){
            if(each != parent){
                long size = dfs(each, node, N, costs);
                sum += size;
                cost += size * (N - size);
            }
        }

        cost += sum * (N - sum);
        cost += (N-1);

        pq.add(new tup(node, cost));
        return sum;
    }

    static void asserts(boolean cond) {
        if(!cond) {
            throw new IllegalArgumentException();
        }
    }

    static class tup implements Comparable<tup>, Comparator<tup>{
		int a; long b;
		tup(int a,long b){
			this.a=a;
			this.b=b;
		}
		public tup() {
		}
		@Override
		public int compareTo(tup o){
			return Long.compare(o.b,b);
		}
		@Override
		public int compare(tup o1, tup o2) {
			return Long.compare(o2.b, o1.b);
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
 
        int nextInt() {
            return Integer.parseInt(next());
        }
 
        long nextLong() {
            return Long.parseLong(next());
        }
 
        double nextDouble() {
            return Double.parseDouble(next());
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
