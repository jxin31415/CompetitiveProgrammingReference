package References;
import java.util.*;
import java.io.*;

// My initial dive into graph theory


// Graph theory:
// Each VERTEX points to its factors; for example, 6 points to 1, 2, 3, and 6.
// Each EDGE can also contain a WEIGHT containing the difference between the factor and the number.
// There will be no MULTIGRAPHS in this example. All three, however, can represent one.

public class GraphIntro { 
	
	public static List<Integer> findFactors(int num){
		List<Integer> lis = new ArrayList<Integer>();
		for(int i = 1; i <= num; i++) {
			if(num % i == 0)
				lis.add(i);
		}
		return lis;
	}
	
	
	public static void main (String [] args) throws IOException {
		int[] numbers = {2, 6, 14, 25}; // Numbers go up to 25
		
		List<int[]> edgeList = new ArrayList<int[]>();
		int[][] adjacencyMatrix = new int[26][26];
		Map<Integer, List<String>> adjacencyList = new HashMap<Integer, List<String>>();
		
		for(int[] a : adjacencyMatrix) {
			for(int b = 0; b < a.length; b++){
				a[b] = -1;
			}
		}
		
		for(int each: numbers) {
			List<Integer> temp = findFactors(each);
			adjacencyList.put(each, new ArrayList<String>());
			for(int factors: temp) {
				edgeList.add(new int[] {each, factors, each - factors});
				adjacencyMatrix[each][factors] = each - factors;
				adjacencyList.get(each).add(factors + "(" + (each - factors) + ")");
			}
		}
		
		System.out.println("Edge List: ");
		for(int[] each: edgeList) {
			System.out.print("{" + Arrays.toString(each) + "}");
		}
		
		System.out.println("\nAdjacency Matrix:");
		for(int[] each: adjacencyMatrix) {
			System.out.println(Arrays.toString(each));
		}
		
		System.out.println("\nAdjacency List: ");
		for(int each: adjacencyList.keySet()) {
			System.out.println(each + ": " + adjacencyList.get(each));
		}
	}
	
}
