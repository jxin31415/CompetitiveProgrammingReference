import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
 
public class jimmy {
    
    public static void main(String[] args) {
        
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        
        int[] in = sc.readArray(2, 1, 100000);
        int N = in[0];
        int M = in[1];

        long[] C = sc.readLongArray(N, 1, 1000000000);
        int[] A = sc.readArray(M, 1, N);
        for(int i = 0; i < M; i++){
            A[i]--; // 0-indexed
        }
        long[] K = sc.readLongArray(M, 1, 1000000000);

        tup[] t = new tup[M];
        for(int i = 0; i < M; i++){
            t[i] = new tup(A[i], K[i]);
        }

        Arrays.sort(t, new tup());

        long[] pSums = new long[N];
        pSums[0] = C[0];
        for(int i = 1; i < N; i++){
            pSums[i] = pSums[i-1] + C[i];
        }
        
        int[] B = new int[M];
        for(int i = 0; i < M; i++){
            int ans = -1;
            int lo = t[i].a, hi = N-1;
            
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (sum(t[i].a, mid, pSums) >= t[i].b) {
                    hi = mid-1;
                    ans = mid;
                } else {
                    lo = mid+1;
                }
            }

            B[i] = ans;
        }

        // System.out.println(Arrays.toString(t));
        // System.out.println(Arrays.toString(B));
        // System.out.println(Arrays.toString(pSums));
        
        int[] DP = new int[N+1];
        int j = 0;
        for(int i = 0; i < N; i++){
            while(j < M && t[j].a == i){
                int endI = B[j++];
                if(endI != -1) {
                    DP[endI + 1] = Math.max(DP[endI + 1], DP[i] + 1);
                }
            }
            DP[i+1] = Math.max(DP[i], DP[i+1]);
        }

        out.println(DP[N]);

        out.close();
    }

    public static long sum(int lo, int hi, long[] sums){ // inclusive
        if(lo == 0){
            return sums[hi];
        }
        return sums[hi] - sums[lo - 1];
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
			return Integer.compare(a,o.a);
		}
		@Override
		public int compare(tup o1, tup o2) {
			return Integer.compare(o1.a, o2.a);
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
