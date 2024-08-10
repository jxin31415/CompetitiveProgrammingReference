import java.io.*;
import java.util.*;
 
public class jimmy {
    // d is the number of characters in the input alphabet 
    public final static int d = 256;

    // prime is some prime which we will use for modulus
    public final static int PRIME = 1000000007;
    // Rabin-Karp algorithm for pattern searching
    public static Set<Integer> rabinKarp(String text, String pattern){
        if(pattern.length() > text.length()) {
            return new HashSet<>();
        }
        
        long hashPattern = 0;
        long hashText = 0;
        Set<Integer> answer = new HashSet<>();
        
        long h = 1;
        for(int i = 0; i < pattern.length()-1; i++) {
            h = (h * d) % PRIME;
        }
        
        for(int i = 0; i < pattern.length(); i++) {
            hashPattern = (d * hashPattern + pattern.charAt(i)) % PRIME;
            hashText = (d * hashText + text.charAt(i)) % PRIME;
        }
        
        for(int i = 0; i <= text.length() - pattern.length(); i++) {
            if(hashPattern == hashText) {
                int j;
                for(j = 0; j < pattern.length(); j++) {
                    if(text.charAt(i+j) != pattern.charAt(j))
                        break;
                }
                if(j == pattern.length()) {
                    answer.add(i);
                }
            }
            if(i < text.length() - pattern.length()) {
                hashText = (d * (hashText - text.charAt(i) * h) + text.charAt(i+pattern.length()))%PRIME;
                if(hashText < 0)
                    hashText+=PRIME;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        int N = sc.nextInt(1, 1000);

        String s = sc.nextLine();
        asserts(s.length() >= 1 && s.length() <= 1000);

        for(char each: s.toCharArray()) {
            asserts(each == 'A' || each == 'T' || each == 'C' || each == 'G');
        }

        Set<String> segs = new HashSet<>();
        List<Set<Integer>> seg_inds = new ArrayList<>();
        List<String> seg_list = new ArrayList<>();

        for(int n = 0; n < N; n++) {
            String seg = sc.nextLine();
            asserts(seg.length() >= 1 && seg.length() <= 1000);
            for(char each: seg.toCharArray()) {
                asserts(each == 'A' || each == 'T' || each == 'C' || each == 'G');
            }
            segs.add(seg);
            seg_list.add(seg);

            seg_inds.add(rabinKarp(s, seg));
            // System.out.println(seg_inds.get(n));
        }

        asserts(segs.size() == N);
        
        int[] dp = new int[s.length() + 1];
        int[] chosen = new int[s.length() + 1];

        Arrays.fill(chosen, -1);
        Arrays.fill(dp, Integer.MAX_VALUE / 2);

        dp[0] = 0;
        for(int c = 0; c < s.length(); c++) {
            if(dp[c] != -1) {
                for(int k = 0; k < N; k++) {
                    if(chosen[c] == k)
                        continue;
                    
                    if(seg_inds.get(k).contains(c)) {
                        if(dp[c] + 1 < dp[c + seg_list.get(k).length()]) {
                            chosen[c + seg_list.get(k).length()] = k;
                        } else if (dp[c] + 1 == dp[c + seg_list.get(k).length()]) {
                            chosen[c + seg_list.get(k).length()] = -1;
                        }

                        dp[c + seg_list.get(k).length()] = Math.min(dp[c + seg_list.get(k).length()], dp[c] + 1);
                    }
                }
            }
        }

        // System.out.println(Arrays.toString(dp));
        if(dp[dp.length - 1] == Integer.MAX_VALUE / 2) {
            out.println(-1);
        } else {
            out.println(dp[dp.length - 1]);
        }

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