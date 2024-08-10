import java.io.*;
import java.util.*;

public class jimmy {
    
    public static void main(String[] args) {
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out), true);
        
        int N = sc.nextInt();
        
        if(N < 1 || N > 100000) {
            throw new IllegalArgumentException();
        }
        
        String[] s = sc.nextLine().split(" ");
        if(s.length != N){
            throw new IllegalArgumentException();
        }

        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(s[i]);
        }
        
        Set<Long> X = new HashSet<>();
        
        loop:
        for(int i = 0; i < N; i++) {
            
            if(arr[i] < 1 || arr[i] > 1000000000) {
                throw new IllegalArgumentException();
            }
            
            for(int k = 31; k >= 2; k--) {
                long x = (long) Math.round(Math.pow(arr[i], 1.0 / k));
                if(Math.pow(x, k) == arr[i]) {
                    X.add(x);
                    continue loop;
                }
            }
            
            // Not perfect power
            throw new IllegalArgumentException();
        }
        
        dbg(X);
        out.println(X.size());
        
        out.close();
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
        
        int[] readArray(int size) {
            int[] a = new int[size];
            for(int i = 0; i < size; i++) {
                a[i] = nextInt();
            }
            return a;
        }
        
        long[] readLongArray(int size) {
            long[] a = new long[size];
            for(int i = 0; i < size; i++) {
                a[i] = nextLong();
            }
            return a;
        }
    }
    
    static void dbg(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
    static void dbg(long[] arr) {
        System.out.println(Arrays.toString(arr));
    }
    static void dbg(boolean[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    static void dbg(Object... args) {
        for (Object arg : args)
            System.out.print(arg + " ");
        System.out.println();
    }
}