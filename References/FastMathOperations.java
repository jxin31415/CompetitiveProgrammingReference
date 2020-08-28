package References;

public class FastMathOperations {

	// (a ^ b) % m
	static long binpow(long a, long b, long m) {
		a %= m;
		long res = 1;
		while (b > 0) {
			if ((b & 1) == 1)
				res = res * a % m;
			a = a * a % m;
			b >>= 1; // b /= 2;
		}
		return res;
	}
	
	// (a * b) % mod 
	static long moduloMultiplication(long a, long b, long mod) { 
		long res = 0; 
		a %= mod; 

		while (b > 0) { 
			
			if ((b & 1) > 0) { 
				res = (res + a) % mod; 
			} 
			a = (2 * a) % mod; 

			b >>= 1; // b /= 2;
		} 
		return res; 
	} 
	
	// modular inverse of N
	public static long modInverse(long N, long MOD) {
		return binpow(N, MOD - 2, MOD);
	}
	
	// a / b, but with weird modulo rules
	public static long modDivide(long a, long b, long MOD) {
		a %= MOD;
		return (modInverse(b, MOD) * a) % MOD;
	}
	
	// n C r % MOD, pre-calculate fac[] for a log[N] combinations calculator
	static long C(int n, int r, long MOD) { 
		if (r == 0) 
			return 1; 
		long[] fac = new long[n+1];
		fac[0] = 1; 
		
		for (int i = 1; i <= n; i++) 
			fac[i] = fac[i - 1] * i % MOD; 
		return (fac[n] * modInverse(fac[r], MOD) % MOD * modInverse(fac[n - r], MOD) % MOD) % MOD; 
	}
	
	static int gcd(int a, int b) { 
		if (a == 0) 
			return b;  
		return gcd(b % a, a);  
    } 
      
    static int lcm(int a, int b) { 
        return (a*b) / gcd(a, b); 
    }
}
