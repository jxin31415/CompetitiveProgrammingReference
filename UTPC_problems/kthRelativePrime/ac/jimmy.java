import java.io.*;
import java.util.*;

public class jimmy {
    
    public static void main(String[] args) {
        
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        
        int N = sc.nextInt();
        int M = sc.nextInt();
        
        if(N < 1 || N > 1000000) {
            throw new IllegalArgumentException();
        }
        
        if(M < 2 || M > 100000) {
            throw new IllegalArgumentException();
        }
        
        int[] arr = sc.readArray(N);
        for(int each: arr) {
            if(each < 1 || each > 1000000000) {
                throw new IllegalArgumentException();
            }
        }
        
        boolean[] relPrime = new boolean[M + 1];
        Arrays.fill(relPrime, true);
        
        int M2 = M;
        List<Integer> primeFactors = new ArrayList<>();
        for(int i = 2; i <= M2; i++) {
            if(M2 % i == 0) {
                primeFactors.add(i);
            }
            while(M2 % i == 0) {
               M2 = M2 / i;
            }
        }

        if(M2 != 1){
            throw new IllegalStateException();
        }
        for(int i = 1; i < primeFactors.size(); i++){
            if(primeFactors.get(i) <= primeFactors.get(i-1)){
                throw new IllegalStateException();
            }
        }
         
        for(int factor: primeFactors) {
            for(int i = 0; i < relPrime.length; i += factor) {
                relPrime[i] = false;
            }
        }

        if(relPrime[0] || !relPrime[1] || relPrime[M]){
            throw new IllegalStateException();
        }
        
//        System.out.println(primeFactors);
        
        List<Integer> relPrimeList = new ArrayList<>();
        for(int i = 0; i < relPrime.length; i++) {
            if(relPrime[i]) {
                relPrimeList.add(i);
            }
        }
        
//        System.out.println(relPrimeList);
        
        for(int each: arr) {
            long ans = (long) M * ((each - 1) / relPrimeList.size());
            ans += relPrimeList.get((each - 1) % relPrimeList.size());
            out.print(ans + " ");

            if(ans <= 0){
                throw new IllegalStateException();
            }
        }
        
        out.println();
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
                    throw new IllegalArgumentException();
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
    
}
