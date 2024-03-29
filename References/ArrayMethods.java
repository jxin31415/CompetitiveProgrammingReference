package References;

import java.util.Arrays;
import java.util.Random;

public class ArrayMethods {
	
	static void shuffle(int[] a) {
		Random get = new Random();
		for (int i = 0; i < a.length; i++) {
			int r = get.nextInt(a.length);
			int temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
	}

	static void shuffle(long[] a) {
		Random get = new Random();
		for (int i = 0; i < a.length; i++) {
			int r = get.nextInt(a.length);
			long temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
	}
	
	static int maxSubArraySum(int a[])  // Kadane's algorithm
    { 
        int max_so_far = Integer.MIN_VALUE, max_ending_here = 0; 
  
        for (int i = 0; i < a.length; i++) 
        { 
            max_ending_here = max_ending_here + a[i]; 
            if (max_so_far < max_ending_here) 
                max_so_far = max_ending_here; 
            if (max_ending_here < 0) 
                max_ending_here = 0; 
        } 
        return max_so_far; 
    }
	
	static class MyArray {        
	    private int[] arrayInstance;

	    public MyArray(int[] a) {
	            this.arrayInstance = a;
	    }

	    @Override
	    public int hashCode() {
	            return Arrays.hashCode(arrayInstance);
	    }

	    @Override
	    public boolean equals(Object obj) {
	            if (this == obj)
	                    return true;
	            if (obj == null)
	                    return false;
	            if (getClass() != obj.getClass())
	                    return false;
	            MyArray other = (MyArray) obj;
	            if (!Arrays.equals(arrayInstance, other.arrayInstance))
	                    return false;
	            return true;
	    }
	}
}
