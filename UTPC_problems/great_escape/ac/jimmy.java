import java.io.*;
import java.util.*;
 
public class jimmy {
    static double[] x;
    static double[] y;
    static double[] r;
    static int N, M;

    public static double dist(int i, int j) {
        return Math.sqrt((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]));
    }

    public static boolean top_left(int i) {
        return x[i] - r[i] <= 0 || y[i] + r[i] >= M;
    }

    public static boolean bottom_right(int i) {
        return x[i] + r[i] >= N || y[i] - r[i] <= 0;
    }
    public static void main(String[] args) {
        
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        N = sc.nextInt(1, 1000000000);
        M = sc.nextInt(1, 1000000000);
        int K = sc.nextInt(1, 1000);

        x = new double[K];
        y = new double[K];
        r = new double[K];

        for(int i = 0; i < K; i++) {
            int X = sc.nextInt(0, 1000000000);
            int Y = sc.nextInt(0, 1000000000);
            int R = sc.nextInt(1, 1000000000);

            x[i] = X;
            y[i] = Y;
            r[i] = R;
        }
        
        asserts(sc.next() == null);

        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < K; i++) {
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < K; i++) {
            for(int j = 0; j < K; j++) {
                if(dist(i, j) <= r[i] + r[j]) {
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }
        
        boolean[] visited = new boolean[K];
        Queue<tup> q = new LinkedList<>();
        for(int i = 0; i < K; i++) {
            if (top_left(i)) {
                q.add(new tup(i, 1));
            }
        }

        while(q.size() > 0) {
            tup t = q.poll();
            if (bottom_right(t.a)) {
                out.println(t.b);
                out.close();
                return;
            }
            if(visited[t.a]){
                continue;
            }
            visited[t.a] = true;
            for(int each: adj.get(t.a)) {
                q.add(new tup(each, t.b + 1));
            }
        }

        out.println(-1);
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
                } catch (Exception e) {
                    return null;
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