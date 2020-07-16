package References;
import java.util.*;
import java.io.*;

public class DFS_BFS { //program to find sets of numbers that add up to a given larger number (no permutations!)
	
	public static void DFS(int num, List<Integer> lis, int currentSum, int start) { // Depth first search
		if(currentSum == num) {
			System.out.println(lis);
			return;
		}
		if(currentSum > num) {
			return;
		}
		for(int i = start; i <= num; i++) {
			lis.add(i);
			DFS(num, lis, currentSum + i, i);
			lis.remove((Integer) i);
		}
	}
	
	public static void BFS(int num, List<List<Integer>> lis) { // Use queue data structure if you know how to
		if(findSum(lis.get(0)) == num) {
			System.out.println(lis.get(0));
			lis.remove(0);
			return;
		}
		if(findSum(lis.get(0)) > num) {
			lis.remove(0);
			return;
		}
		int start = lis.get(0).get(lis.get(0).size()-1);
		for(int i = start; i < num; i++) {
			List<Integer> temp = new ArrayList<Integer>();
			for(Integer each: lis.get(0)) {
				temp.add(each);
			}
			temp.add(i);
			lis.add(temp);
		}
		lis.remove(0);
	}
	
	public static void DFS_ID(int num, List<Integer> lis, int currentSum, int start, int limit) { // Depth first search
		if(currentSum == num) {
			System.out.println(lis);
			return;
		}
		if(currentSum > num) {
			return;
		}
		if(lis.size() >= limit)
			return;
		for(int i = start; i <= num; i++) {
			lis.add(i);
			DFS_ID(num, lis, currentSum + i, i, limit);
			lis.remove((Integer) i);
		}
	}
	
	public static int findSum(List<Integer> lis) {
		int sum = 0;
		for(int each: lis)
			sum += each;
		return sum;
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in)); //for testing purposes

	    int i1 = Integer.parseInt(f.readLine());

	    System.out.println("Depth first search: ");
	    DFS(i1, new ArrayList<Integer>(), 0, 1);
	    
	    System.out.println("Breadth first search: ");
	    List<List<Integer>> lis = new ArrayList<List<Integer>>();
	    for(int i = 1; i <= i1; i++) {
	    	List<Integer> temp = new ArrayList<Integer>();
	    	temp.add(i);
	    	lis.add(temp);
	    }
	    while(lis.size() > 0)
	    	BFS(i1, lis);
	    
	    System.out.println("Depth first search with iterative deepening: ");
	    for(int i = 1; i <= i1; i++)
	    	DFS_ID(i1, new ArrayList<Integer>(), 0, 1, i);	    
	    
	}
	
}
