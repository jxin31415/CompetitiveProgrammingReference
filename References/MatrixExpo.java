package References;

public class MatrixExpo {
	static final long MOD = 1000000007l;
	
	static long temp[][] =new long[101][101]; // square matrices of the same size
	static void multiply(long[][] a, long[][]b) { // multiplies matrices a and b and stores them into a
		long val;
		int x = a.length;
		
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < x; j++) {
				val = 0;
				for (int k = 0; k < x; k++)
					val = (val + (a[i][k]) * b[k][j]) % MOD;
				temp[i][j] = val;
			}
		}
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < x; j++) 
				a[i][j] = temp[i][j];
		}
	}
	
	static long binaryExpo(long base, long exp) 
    { 
        long res = 1; 
        while (exp > 0) { 
            if ((exp & 1) > 0) 
                res = (res * base) % MOD; 
            base = (base * base) % MOD; 
            exp >>= 1; 
        } 
        return res % MOD; 
    } 
	
	static long[][] binaryExpoMatrix(long[][] base, long exp) 
    { 
		long[][] res = genIdentityMatrix(base.length);
        while (exp > 0) { 
            if ((exp & 1) > 0) {
                multiply(res, base);
            }
            multiply(base, base);
            exp >>= 1; 
        } 
        return res; 
    }
	
	static long[][] genIdentityMatrix(int x){
		long[][] res = new long[x][x];
		for(int j=0;j<x;j++){
			res[j][j]++;
		}
		return res;	
	}
	
	public static void main(String[] args) {
		
	}
}
