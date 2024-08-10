import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class jimmy {
    
    public static void main(String[] args) {
        
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        
        int[] arr = sc.readArray(52);

        int[] count = new int[13];
        for(int each: arr){
            count[each - 1]++;
        }
        
        for(int freq: count){
            if(freq != 4){
                throw new IllegalArgumentException();
            }
        }

        List<Integer> aces = new ArrayList<>();
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == 1){
                aces.add(i);
            }
        }

        for(int c = 0; c <= 1000000; c++){
            int firstHalf = 0;
            for(int i = 0; i < aces.size(); i++){
                if(aces.get(i) < 26) {
                    firstHalf++;
                }
                int newPos = aces.get(i) * 2;
                if(newPos >= 52){
                    newPos = newPos - 52 + 1;
                }
                aces.set(i, newPos);
            }

            if(firstHalf == 4){
                out.println("YES");
                out.close();
                return;
            }
        }

        out.println("NO");

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
