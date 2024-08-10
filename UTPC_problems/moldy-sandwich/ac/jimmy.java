import java.io.*;
import java.util.*;
 
public class jimmy {
    
    static List<Integer> x = new ArrayList<>();
    static List<Integer> y = new ArrayList<>();
    public static void main(String[] args) {
        
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        
        int N = sc.nextInt();
        asserts(N > 0 && N <= 1000);

        char[][] board = new char[N][N];
        for(int i = 0; i < N; i++){
            String s = sc.nextLine();
            asserts(s.length() == N);
            board[i] = s.toCharArray();
            for(int j = 0; j < N; j++){
                asserts(board[i][j] == '#' || board[i][j] == '.');
                if(board[i][j] == '#'){
                    x.add(i);
                    y.add(j);
                }
            }
        }
        
        Collections.sort(x);
        Collections.sort(y);

        int[][] prefixSums = new int[N][N];
        prefixSums[0][0] = board[0][0] == '#' ? 1 : 0;

        for (int i = 1; i < N; i++) {
            prefixSums[0][i] = prefixSums[0][i-1] + (board[0][i] == '#' ? 1 : 0);
            prefixSums[i][0] = prefixSums[i-1][0] + (board[i][0] == '#' ? 1 : 0);
        }
 
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                prefixSums[i][j] = prefixSums[i-1][j] + prefixSums[i][j-1] - prefixSums[i-1][j-1] + (board[i][j] == '#' ? 1 : 0);
            }
        }
        
        // for(int[] each: prefixSums){
        //     System.out.println(Arrays.toString(each));
        // }
        
        int M = prefixSums[N-1][N-1];
        asserts(M >= 1 && M <= 100);

        for(int m = 1; m <= M; m++){
            int ans = -1;
            int lo = 1, hi = N;
            
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (maxSandwiches(prefixSums, mid, m)) {
                    hi = mid-1;
                    ans = mid;
                } else {
                    lo = mid+1;
                }
            }

            out.print(ans + " ");
        }
        out.println();

        out.close();
    }

    static boolean maxSandwiches(int[][] prefixSums, int K, int m){
        for(int i_lo = 0; i_lo < x.size(); i_lo++){
            for(int j_lo = 0; j_lo < y.size(); j_lo++){
                int bottom = x.get(i_lo);
                int right = y.get(j_lo);

                int numS = query(prefixSums, bottom - K + 1, right - K + 1, bottom, right);
                if(numS >= m){
                    return true;
                }
            }
        }
        return false;
    }

    static int query(int[][] pSums, int r1, int c1, int r2, int c2){
        asserts(r2 >= r1 && c2 >= c1);
        int val = pSums[r2][c2];
        if(r1 > 0){
            val -= pSums[r1-1][c2];
        }
        if(c1 > 0){
            val -= pSums[r2][c1-1];
        }
        if(r1 > 0 && c1 > 0){
            val += pSums[r1-1][c1-1];
        }
        return val;
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
