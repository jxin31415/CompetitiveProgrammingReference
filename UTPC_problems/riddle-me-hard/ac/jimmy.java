import java.io.*;
import java.util.*;
 
public class jimmy {
    static final int oo = Integer.MAX_VALUE;

    static List<List<Integer>> adj = new ArrayList<>();
    public static void main(String[] args) {
        
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        int N = sc.nextInt(2, 50);
        asserts(N % 2 == 0);

        int[] s = new int[N];
        int[] a = new int[N];
        Arrays.fill(a, -1);
        int invalid = 0;

        for(int n = 0; n < N; n++) {
            s[n] = sc.nextInt(1, 1000);

            int[] arr = new int[s[n]];
            for(int i = 0; i < s[n]; i++){ 
                arr[i] = sc.nextInt(1, s[n]);
            }
            Set<Integer> set = new HashSet<>();
            for(int each: arr) {
                set.add(each);
            }
            asserts(set.size() == s[n]);

            for(int i = 0; i < s[n]; i++) {
                if(arr[i] < arr[(i-1 + s[n]) % s[n]]) {
                    if(a[n] != -1)
                        a[n] = oo;
                    else
                        a[n] = i;
                }
            }
            if(a[n] == oo) {
                invalid++;
            }
        }

        System.out.println(Arrays.toString(s));
        System.out.println(Arrays.toString(a));

        for(int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < N; i++) {
            for(int j = i+1; j < N; j++) {
                if(a[i] == oo || a[j] == oo)
                    continue;
                
                int gcd = gcd(s[i], s[j]);
                int diff = Math.max(a[i], a[j]) - Math.min(a[i], a[j]);
                if((diff / gcd) * gcd == diff) {
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }

        System.out.println(adj);
        System.out.println(invalid);

        int non_invalid = N - invalid;
        // int total = 0;
        // int leftover = 0;
        // for(int i = 0; i < count.length; i++) {
        //     total += (count[i] / 2) * 2;
        //     leftover += (count[i] % 2);
        // }

        // total += Math.min(leftover, invalid);
        // total += Math.max(0, (leftover - invalid)) / 2;

        // out.println(total);
        out.close();
    }

    static int gcd(int a, int b) { 
        if (a == 0) 
            return b;  
        return gcd(b % a, a);  
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