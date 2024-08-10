import java.io.*;
import java.util.*;

/**
 * 
 * Problem statement:
 * Given an array of 1<=n<=51 integers between -1e9 and 1e9,
 * count the number of longest (strictly) increasing subsequences.
 * Output this number modulo 1e9+7.
 * 
 * Sample Input:
 * 5
 * 1 3 2 2 5
 * Answer:
 * 3
 * Explanation:
 * The 3 longest increasing subsequences are [1 3 5], [1 2 5], and [1 2 5].
 *
 *
 * This problem should be a pretty straightforward dp, but I'm not sure where my bug is.
 * Maybe an off-by-one error somewhere...?
 * Help me find a test case that I fail on!
 * 
 */
public class throwaway {

	public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
        	nums[i] = in.nextInt();
        }
    }
}