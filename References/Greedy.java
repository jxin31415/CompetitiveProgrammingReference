package References;
import java.util.*;
import java.io.*;

public class Greedy { // program to find smallest combination of coins to add up to a given total
					  // proof that a lot of times, greedy doesn't work
	static int[] coinValues = new int[3]; // change to desired coin values(sorted)
	
	public static void greedy(int n) {
		int total = 0;
		List<Integer> coins = new ArrayList<Integer>();
		while(total < n) {
			boolean changed = false;
			for(int i = coinValues.length - 1; i >= 0; i--) {
				if(coinValues[i] + total <= n) {
					coins.add(coinValues[i]);
					changed = true;
					total += coinValues[i];
					break;
				}
			}
			if(!changed && total != n) {
				System.out.println(n + ": NOT POSSIBLE");
				return;
			}
		}
		System.out.println(n + ": Solution: " + coins);
	}
	
	public static void main (String [] args) throws IOException {
		coinValues = new int[]{3, 11, 13};
		System.out.println("Coin values: " + Arrays.toString(coinValues) + "\n");
		// solution that works
		greedy(19);
		System.out.println();
		
		// solution that doesn't work
		greedy(22);
		System.out.println("A better solution would be {11, 11}");
		System.out.println();
	}
}
