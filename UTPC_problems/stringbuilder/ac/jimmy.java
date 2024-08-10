import java.io.*;
import java.util.*;
 
public class jimmy {
    static SuffixArray suffix1;
    public static void main(String[] args) throws IOException {
        
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        int N = sc.nextInt(1, 1000);
        
        String orig = sc.nextLine();
        asserts(orig.length() > 0 && orig.length() <= 10000);
        
        suffix1 = new SuffixArray(orig);

        while(N --> 0){ 
            String target = sc.nextLine();
            asserts(target.length() > 0 && target.length() <= 1000);

            // find max common substring
            String lcs = LongestCommonSubstring.lcs(orig, target);
            out.println(orig.length() - lcs.length() + target.length() - lcs.length());
        }

        out.close();
    }

    public static class LongestCommonSubstring {

        // Do not instantiate.
        private LongestCommonSubstring() { }
    
        // return the longest common prefix of suffix s[p..] and suffix t[q..]
        private static String lcp(String s, int p, String t, int q) {
            int n = Math.min(s.length() - p, t.length() - q);
            for (int i = 0; i < n; i++) {
                if (s.charAt(p + i) != t.charAt(q + i))
                    return s.substring(p, p + i);
            }
            return s.substring(p, p + n);
        }
    
        // compare suffix s[p..] and suffix t[q..]
        private static int compare(String s, int p, String t, int q) {
            int n = Math.min(s.length() - p, t.length() - q);
            for (int i = 0; i < n; i++) {
                if (s.charAt(p + i) != t.charAt(q + i))
                    return s.charAt(p+i) - t.charAt(q+i);
            }
            if      (s.length() - p < t.length() - q) return -1;
            else if (s.length() - p > t.length() - q) return +1;
            else                                      return  0;
        }
    
        /**
         * Returns the longest common string of the two specified strings.
         *
         * @param  s one string
         * @param  t the other string
         * @return the longest common string that appears as a substring
         *         in both {@code s} and {@code t}; the empty string
         *         if no such string
         */
        public static String lcs(String s, String t) {
            SuffixArray suffix2 = new SuffixArray(t);
    
            // find longest common substring by "merging" sorted suffixes
            String lcs = "";
            int i = 0, j = 0;
            while (i < s.length() && j < t.length()) {
                int p = suffix1.index(i);
                int q = suffix2.index(j);
                String x = lcp(s, p, t, q);
                if (x.length() > lcs.length()) lcs = x;
                if (compare(s, p, t, q) < 0) i++;
                else                         j++;
            }
            return lcs;
        }
    
    }
        
    public static class SuffixArray {
        private Suffix[] suffixes;
    
        /**
         * Initializes a suffix array for the given {@code text} string.
         * @param text the input string
         */
        public SuffixArray(String text) {
            int n = text.length();
            this.suffixes = new Suffix[n];
            for (int i = 0; i < n; i++)
                suffixes[i] = new Suffix(text, i);
            
            shuffle(suffixes);
            Arrays.sort(suffixes);
        }

        static void shuffle(Suffix[] a) {
            Random get = new Random();
            for (int i = 0; i < a.length; i++) {
                int r = get.nextInt(a.length);
                Suffix temp = a[i];
                a[i] = a[r];
                a[r] = temp;
            }
        }
    
        private static class Suffix implements Comparable<Suffix> {
            private final String text;
            private final int index;
    
            private Suffix(String text, int index) {
                this.text = text;
                this.index = index;
            }
            private int length() {
                return text.length() - index;
            }
            private char charAt(int i) {
                return text.charAt(index + i);
            }
    
            public int compareTo(Suffix that) {
                if (this == that) return 0;  // optimization
                int n = Math.min(this.length(), that.length());
                for (int i = 0; i < n; i++) {
                    if (this.charAt(i) < that.charAt(i)) return -1;
                    if (this.charAt(i) > that.charAt(i)) return +1;
                }
                return this.length() - that.length();
            }
    
            public String toString() {
                return text.substring(index);
            }
        }
    
        /**
         * Returns the length of the input string.
         * @return the length of the input string
         */
        public int length() {
            return suffixes.length;
        }
    
    
        /**
         * Returns the index into the original string of the <em>i</em>th smallest suffix.
         * That is, {@code text.substring(sa.index(i))} is the <em>i</em>th smallest suffix.
         * @param i an integer between 0 and <em>n</em>-1
         * @return the index into the original string of the <em>i</em>th smallest suffix
         * @throws java.lang.IllegalArgumentException unless {@code 0 <= i < n}
         */
        public int index(int i) {
            return suffixes[i].index;
        }
    
    
        /**
         * Returns the length of the longest common prefix of the <em>i</em>th
         * smallest suffix and the <em>i</em>-1st smallest suffix.
         * @param i an integer between 1 and <em>n</em>-1
         * @return the length of the longest common prefix of the <em>i</em>th
         * smallest suffix and the <em>i</em>-1st smallest suffix.
         * @throws java.lang.IllegalArgumentException unless {@code 1 <= i < n}
         */
        public int lcp(int i) {
            return lcpSuffix(suffixes[i], suffixes[i-1]);
        }
    
        // longest common prefix of s and t
        private static int lcpSuffix(Suffix s, Suffix t) {
            int n = Math.min(s.length(), t.length());
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) != t.charAt(i)) return i;
            }
            return n;
        }
    
        /**
         * Returns the <em>i</em>th smallest suffix as a string.
         * @param i the index
         * @return the <em>i</em> smallest suffix as a string
         * @throws java.lang.IllegalArgumentException unless {@code 0 <= i < n}
         */
        public String select(int i) {
            return suffixes[i].toString();
        }
    
        /**
         * Returns the number of suffixes strictly less than the {@code query} string.
         * We note that {@code rank(select(i))} equals {@code i} for each {@code i}
         * between 0 and <em>n</em>-1.
         * @param query the query string
         * @return the number of suffixes strictly less than {@code query}
         */
        public int rank(String query) {
            int lo = 0, hi = suffixes.length - 1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                int cmp = compare(query, suffixes[mid]);
                if (cmp < 0) hi = mid - 1;
                else if (cmp > 0) lo = mid + 1;
                else return mid;
            }
            return lo;
        }
    
        // compare query string to suffix
        private static int compare(String query, Suffix suffix) {
            int n = Math.min(query.length(), suffix.length());
            for (int i = 0; i < n; i++) {
                if (query.charAt(i) < suffix.charAt(i)) return -1;
                if (query.charAt(i) > suffix.charAt(i)) return +1;
            }
            return query.length() - suffix.length();
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