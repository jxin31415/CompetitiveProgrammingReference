import java.io.*;
import java.util.*;
 
public class jimmy {

    public static void main(String[] args) {
        
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        double[] x = new double[10];
        double[] y = new double[10];

        for(int i = 0; i < 10; i++) {
            x[i] = sc.nextDouble(-100, 100);
            y[i] = sc.nextDouble(-100, 100);
        }

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                if (i == j) continue;
                asserts(Math.abs(x[i] - x[j]) > 0.05 || Math.abs(y[i] - y[j]) > 0.05);
            }
        }

        double x1 = x[0];
        double y1 = y[0];
        double x2 = x[1];
        double y2 = y[1];
        double x3 = x[2];
        double y3 = y[2];

        double x12 = x1 - x2;
        double x13 = x1 - x3;
     
        double y12 = y1 - y2;
        double y13 = y1 - y3;
     
        double y31 = y3 - y1;
        double y21 = y2 - y1;
     
        double x31 = x3 - x1;
        double x21 = x2 - x1;
     
        // x1^2 - x3^2
        double sx13 = (Math.pow(x1, 2) -
                        Math.pow(x3, 2));
     
        // y1^2 - y3^2
        double sy13 = (Math.pow(y1, 2) -
                        Math.pow(y3, 2));
     
        double sx21 = (Math.pow(x2, 2) -
                        Math.pow(x1, 2));
                         
        double sy21 = (Math.pow(y2, 2) -
                        Math.pow(y1, 2));
     
        double f = ((sx13) * (x12)
                + (sy13) * (x12)
                + (sx21) * (x13)
                + (sy21) * (x13))
                / (2 * ((y31) * (x12) - (y21) * (x13)));
        double g = ((sx13) * (y12)
                + (sy13) * (y12)
                + (sx21) * (y13)
                + (sy21) * (y13))
                / (2 * ((x31) * (y12) - (x21) * (y13)));
     
        double c = -Math.pow(x1, 2) - Math.pow(y1, 2) -
                                    2 * g * x1 - 2 * f * y1;
     
        // eqn of circle be x^2 + y^2 + 2*g*x + 2*f*y + c = 0
        // where centre is (h = -g, k = -f) and radius r
        // as r^2 = h^2 + k^2 - c
        double h = -g;
        double k = -f;
        double sqr_of_r = h * h + k * k - c;
     
        for (int i = 0; i < 10; i++) {
            double d = x[i] * x[i] + y[i] * y[i] + 2 * g * x[i] + 2 * f * y[i] + c;
            if (Math.abs(d) > 0.001 || Double.isNaN(d) || Double.isInfinite(d)) {
                out.println("CASSETTE");
                out.close();
                return;
            }
        }

        out.println("VINYL");
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