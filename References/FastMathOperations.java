package References;

public class FastMathOperations {

	public static long pow(long a, long b) { // a^b as long as they are both longs/ints
        long re = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                re *= a;        
            }
            b >>= 1;
            a *= a; 
        }
        return re;
    }
	
	// Returns (a * b) % mod 
	static long moduloMultiplication(long a, 
							long b, long mod) 
	{ 
		
		// Initialize result 
		long res = 0; 

		// Update a if it is more than 
		// or equal to mod 
		a %= mod; 

		while (b > 0) 
		{ 
			
			// If b is odd, add a with result 
			if ((b & 1) > 0) 
			{ 
				res = (res + a) % mod; 
			} 

			// Here we assume that doing 2*a 
			// doesn't cause overflow 
			a = (2 * a) % mod; 

			b >>= 1; // b = b / 2 
		} 
		return res; 
	} 
	
	static int power(int x, int y, int p) // x^y % p
    { 
        // Initialize result 
        int res = 1;      
        
        // Update x if it is more   
        // than or equal to p 
        x = x % p;  
      
        while (y > 0) 
        { 
            // If y is odd, multiply x 
            // with result 
            if((y & 1)==1) 
                res = (res * x) % p; 
      
            // y must be even now 
            // y = y / 2 
            y = y >> 1;  
            x = (x * x) % p;  
        } 
        return res; 
    } 
	 //OR
	public static long exp(long base, int power, int mod){
        if(power == 0) return 1;
        if(power == 1) return (base + mod) % mod;
        long ans = exp(base,power/2, mod);
        ans = (ans*ans + mod) % mod;
        if(power%2 == 1) ans = (ans*base + mod) % mod;
        return (ans + mod) % mod;
    } 
}
