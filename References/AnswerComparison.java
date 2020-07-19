package References;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class AnswerComparison {
	
    public static void main(String[] args) throws Exception {
    	FastReader scan = new FastReader("file1.out");
    	FastReader scan2 = new FastReader("file2.out");
    	
    	long start = System.currentTimeMillis();
        
    	String s1 = scan.nextLine();
    	String s2 = scan2.nextLine();
    	int i = 0;
    	while(s1 != null) {
    		if(System.currentTimeMillis() - start > 10000) {
    			System.out.println("Time Limit Exceeded");
    			System.exit(0);
    		}
    		i++;
    		if(!s1.equals(s2)) {
    			System.out.println("Wrong answer : line " + i);
    			System.exit(0);
    		}
    		s1 = scan.nextLine();
    		s2 = scan2.nextLine();
    	}
    	System.out.println("Accepted");
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
	}

}