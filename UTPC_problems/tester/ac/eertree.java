import java.io.*;
import java.util.*;
 
public class eertree {
    static final long MOD = 1000000007;

    static char[] vals;
    static int[][] letterCounts;

    static List<List<Integer>> adj = new ArrayList<>();

    static int[] pals;

    static boolean isPal(int[] counts) {
        int odd = 0;
        for(int each: counts) {
            if(each % 2 == 1)
                odd++;
        }
        return odd <= 1;
    }

    static int dfs(int node, int parent) {
        int p = 0;
        for(int each: adj.get(node)) {
            if(each != parent) {
                p += dfs(each, node);
                for(int i = 0; i < 26; i++) {
                    letterCounts[node][i] += letterCounts[each][i];
                }
            }
        }
        letterCounts[node][vals[node] - 'A']++;

        if(isPal(letterCounts[node]))
            p++;

        pals[node] = p;
        return p;
    }
    public static void main(String[] args) {
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        
        int N = sc.nextInt(1, 200000);

        vals = sc.nextLine().toCharArray();

        for(int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }
        for(int i = 0; i < N-1; i++) {
            int u = sc.nextInt(1, N) - 1;
            int v = sc.nextInt(1, N) - 1;
            asserts(u != v);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        letterCounts = new int[N][26];
        pals = new int[N];
        dfs(0, -1);

        // System.out.println(Arrays.toString(pals));
        // for(int[] each: letterCounts) {
        //     System.out.println(Arrays.toString(each));
        // }

        

        ans = new int[N];
        ans[0] = pals[0];
        rootIsPal = isPal(letterCounts[0]);
        for(int each: adj.get(0)) {
            computeAns(each, 0);
        }

        for(int each: ans) {
            out.println(each);
        }
        out.close();
    }

    static boolean rootIsPal;

    static int[] ans;
    static void computeAns(int node, int parent) {
        ans[node] = ans[parent];

        int[] parentNewCounts = new int[26];
        for(int i = 0; i < 26; i++) {
            parentNewCounts[i] = letterCounts[0][i] - letterCounts[node][i];
        }

        if(isPal(parentNewCounts)) {
            ans[node]++;
        }
        if(isPal(letterCounts[node])) {
            ans[node]--;
        }

        for(int each : adj.get(node)) {
            if(each != parent) {
                computeAns(each, node);
            }
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