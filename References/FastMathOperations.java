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
}
