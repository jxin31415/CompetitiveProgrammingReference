package References;

public class Hashing {
	
	public static class StringHash { // polynomial rolling hash function for a string
		public static final int P = 31; // next-largest prime when compared to size of "alphabet"
		public static final int MOD = 1000000009; // any large prime. the larger it is, the less likely collisions are
		
		public long[] hash;
		public long[] inverse;
		
		public StringHash(String s) { // preprocess in N log N
			long hashVal = 0;
			long pow = 1;
			
			hash = new long[s.length()];
			for(int i = 0; i < s.length(); i++) {
				hashVal = (hashVal + (s.charAt(i) - 'a' + 1) * pow) % MOD;
				pow *= P;
				pow %= MOD;
				hash[i] = hashVal;
			}
			
			inverse = new long[s.length()];
			pow = 1;
			for(int i = 0; i < inverse.length; i++) {
				inverse[i] = modMultInverse(pow, MOD);
				pow *= P;
				pow %= MOD;
			}
		}
		
		public long modMultInverse(long a, long modulo) { // Fermats, works when modulo is prime
			return binpow(a, modulo - 2, modulo);
		}
		
		public long binpow(long a, long b, long m) {
			a %= m;
			long res = 1;
			while (b > 0) {
				if ((b & 1) == 1)
					res = res * a % m;
				a = a * a % m;
				b >>= 1;
			}
			return res;
		}
		
		public long hash(int L, int R) { // L, R inclusive. Returns hash value of substring
			long val = hash[R] - (L == 0 ? 0 : hash[L-1]);
			if(val < 0)
				val += MOD;
			return (val * inverse[L]) % MOD;
		}
	}
	
	
	
	
	public static class StringHashRobust { 	// A more robust version of the above. Drastically reduces chance of collisions
											// (essentially impossible) by utilizing two different hash functions, and comparing the two
		
		public static final int P1 = 31; // next-largest prime when compared to size of "alphabet"
		public static final int MOD1 = 1000000007; // any large prime. the larger it is, the less likely collisions are
		
		public static final int P2 = 37;
		public static final int MOD2 = 1000000009;
		
		public long[] hash1;
		public long[] inverse1;
		public long[] hash2;
		public long[] inverse2;
		
		public StringHashRobust(String s) { // preprocess in N log N
			long hashVal = 0;
			long pow = 1;
			
			hash1 = new long[s.length()];
			for(int i = 0; i < s.length(); i++) {
				hashVal = (hashVal + ((s.charAt(i) - 'a' + 1) * pow)%MOD1) % MOD1;
				pow *= P1;
				pow %= MOD1;
				hash1[i] = hashVal;
			}
			
			inverse1 = new long[s.length()];
			pow = 1;
			for(int i = 0; i < inverse1.length; i++) {
				inverse1[i] = modMultInverse(pow, MOD1);
				pow *= P1;
				pow %= MOD1;
			}
			
			hashVal = 0;
			pow = 1;
			
			hash2 = new long[s.length()];
			for(int i = 0; i < s.length(); i++) {
				hashVal = (hashVal + ((s.charAt(i) - 'a' + 1) * pow)%MOD2) % MOD2;
				pow *= P2;
				pow %= MOD2;
				hash2[i] = hashVal;
			}
			
			inverse2 = new long[s.length()];
			pow = 1;
			for(int i = 0; i < inverse2.length; i++) {
				inverse2[i] = modMultInverse(pow, MOD2);
				pow *= P2;
				pow %= MOD2;
			}
			
		}
		
		public long modMultInverse(long a, long modulo) { // Fermats, works when modulo is prime
			return (binpow(a, modulo - 2, modulo) % modulo + modulo) % modulo;
		}
		
		public long binpow(long a, long b, long m) {
			a %= m;
			long res = 1;
			while (b > 0) {
				if ((b & 1) == 1)
					res = res * a % m;
				a = a * a % m;
				b >>= 1;
			}
			return res;
		}
		
		public long hash1(int L, int R) { // L, R inclusive. Returns hash value of substring
			long val = hash1[R] - (L == 0 ? 0 : hash1[L-1]);
			if(val < 0)
				val += MOD1;
			return ((val * inverse1[L]) % MOD1);
		}
		
		public long hash2(int L, int R) { // L, R inclusive. Returns hash value of substring
			long val = hash2[R] - (L == 0 ? 0 : hash2[L-1]);
			if(val < 0)
				val += MOD2;
			return ((val * inverse2[L]) % MOD2);
		}
		
		public boolean substringComp(int L1, int R1, int L2, int R2) { // compare two substrings. returns true if they are the same
			return hash1(L1, R1) == hash1(L2, R2) && hash2(L2, R2) == hash2(L2, R2);
		}
	}
	
	public static class DecimalHash { 	// strange version of StringHash that allows you to "add" hashes. See https://codeforces.com/contest/898/problem/F
		
		public static final long P = 10; // next-largest prime when compared to size of "alphabet"
		public static final long MOD = 1000000009; // any large prime. the larger it is, the less likely collisions are
		
		public long[] hash;
		public long[] binPow;
		
		public DecimalHash(String s) { // preprocess in N log N
			long hashVal = 0;
			long pow = P;
			
			hash = new long[s.length()];
			for(int i = 0; i < s.length(); i++) {
				hashVal = ((hashVal*pow)%MOD + (s.charAt(i) - '0')) % MOD;
				//pow *= P;
				//pow %= MOD;
				hash[i] = hashVal;
			}
			
			binPow = new long[s.length()+2];
			binPow[0] = 1;
			for(int i = 1; i < binPow.length; i++) {
				binPow[i] = binPow[i-1] * 10;
				binPow[i] %= MOD;
			}
			//System.out.println(Arrays.toString(hash));
		}
		
		public long binpow(long a, long b, long m) {
			a %= m;
			long res = 1;
			while (b > 0) {
				if ((b & 1) == 1)
					res = res * a % m;
				a = a * a % m;
				b >>= 1;
			}
			return res;
		}
		
		public long hash(int L, int R) { // L, R inclusive. Returns hash value of substring
			long val = hash[R] - (L == 0 ? 0 : hash[L-1]) % MOD * binPow[R-L+1] % MOD;
			if(val < 0)
				val += MOD;
			return (val) % MOD;
		}
	}
}

