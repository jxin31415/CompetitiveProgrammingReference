import java.io.*;
import java.util.*;
 
public class jimmy {

    public static void main(String[] args) {
        
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        int[] in = sc.readArray(2, 1, 100000);

        int N = in[0];
        int K = in[1];

        asserts(K <= N);

        long[] arr = sc.readLongArray(N, 1, 1000000000);

        Arrays.sort(arr);

        // System.out.println(Arrays.toString(arr));

        int lo = 0;
        int hi = K-1;
        int med = (K / 2);

        long total = 0;
        for(int i = 0; i < K; i++){
            total += Math.abs(arr[i] - arr[med]);
        }

        long best = total;

        for(hi = K; hi < N; hi++){
            // Calculate new total
            long less = K / 2 + 1;
            long more = K - less;

            // System.out.println(less + " " + more);

            long oldMed = arr[med];
            long newMed = arr[med+1];

            total += (newMed - oldMed) * less;
            total -= (newMed - oldMed) * more;
            med++;
                        
            // Remove lo
            total -= (newMed - arr[lo]);
            lo++;

            // Add hi
            total += (arr[hi] - newMed);

            // System.out.println(arr[med]);
            // System.out.println(total);

            best = Math.min(best, total);
        }

        out.println(best);
        out.close();
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
