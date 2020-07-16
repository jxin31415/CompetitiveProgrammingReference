package References;

import java.util.*;

public class PatternSearching { 

	// d is the number of characters in the input alphabet 
    public final static int d = 256;
    
    // prime is some prime which we will use for modulus
    public final static int PRIME = 1000000007;
	// Rabin-Karp algorithm for pattern searching
	public List<Integer> rabinKarp(String text, String pattern){
		int hashPattern = 0;
		int hashText = 0;
		List<Integer> answer = new ArrayList<>();
		
		int h = 1;
		for(int i = 0; i < pattern.length()-1; i++) {
			h = (h * d) % PRIME;
		}
		
		for(int i = 0; i < pattern.length(); i++) {
			hashPattern = (d * hashPattern + pattern.charAt(i)) % PRIME;
			hashText = (d * hashText + text.charAt(i)) % PRIME;
		}
		
		for(int i = 0; i <= text.length() - pattern.length(); i++) {
			if(hashPattern == hashText) {
				int j;
				for(j = 0; j < pattern.length(); j++) {
					if(text.charAt(i+j) != pattern.charAt(j))
						break;
				}
				if(j == pattern.length()) {
					answer.add(i);
				}
			}
			if(i < text.length() - pattern.length()) {
				hashText = (d * (hashText - text.charAt(i) * h) + text.charAt(i+pattern.length()))%PRIME;
				if(hashText < 0)
					hashText+=PRIME;
			}
		}
	    return answer;
	}
	
	
	// Knuth-Morris-Pratt algorithm for pattern searching
	public static String maxPrefSuf(StringBuilder sb) { // Auxiliary method to find the largest prefix that is also a suffix of String sb
		int N = sb.length();
		int[] maxPrefSuf = new int[N];
		
		int max = 0;
		for(int i = 1; i < N; i++) {
			int j = maxPrefSuf[i-1];
			while(j > 0 && sb.charAt(i) != sb.charAt(j)) {
				j = maxPrefSuf[j-1];
			}
			if(sb.charAt(i) == sb.charAt(j))
				j++;
			maxPrefSuf[i] = j;
			max = Math.max(max, j);
		}
		//System.out.println(Arrays.toString(maxPrefSuf));
		if(max == 0)
			return "";
		return sb.substring(0, maxPrefSuf[N-1]);
	}
	
	public static int[] computeLPS(String pattern, int[] lps) { // Auxiliary method to find the largest prefix that is also a suffix of String pattern for each value in index 0 to pattern.length()
		int len = 0;
		int i = 1;
		lps[0] = 0; // always true
		
		while(i < pattern.length()) {
			if(pattern.charAt(i) == pattern.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			} else {
				if(len != 0)
					len = lps[len-1];
				else { // len is 0, we just started
					lps[i] = len;
					i++;
				}
			}
		}
		return lps;
	}
	
	public static List<Integer> KMPSearch(String pattern, String text){ // Knuth-Morris-Pratt algorithm
		int[] lps = new int[pattern.length()];
		computeLPS(pattern, lps);
		List<Integer> answer = new ArrayList<>();
		// Use two pointers
		int i = 0; // pointer for text
		int j = 0; // pointer for pattern
		while(i < text.length()) {
			if(pattern.charAt(j) == text.charAt(i)) { // Match found. Move both pointers right
				i++;
				j++;
			}
			if(j == pattern.length()) { // answer found
				answer.add(i - j);
				j = lps[j-1];
			} else if (i < text.length() && pattern.charAt(j) != text.charAt(i)) { // Match failed.
				if(j != 0) // Based on our preprocessing there is no need to rematch any indexes from 0 to lps[j-1]
					j = lps[j-1];
				else
					i++;
			}
		}
		
		return answer;
	}
}
