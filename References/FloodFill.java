package References;
import java.util.*;
import java.io.*;

public class FloodFill { 
	static char[][] matrix = {{'#', ' ', ' ', ' '}, {' ', ' ', '#', '#'}, {' ', '#', '#', ' '}, {'#', ' ', '#', ' '}};
	
	public static void main (String [] args) throws IOException {
		System.out.println("Picture this escape room: \n ____");
		for(char[] a : matrix) {
			System.out.print("|");
			for(char b : a)
				System.out.print(b);
			System.out.println("|");
		}
		System.out.println(" ----\nWhat are the different rooms?");
		
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		int currentRoom = 0;
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				if(visited[i][j] == false && matrix[i][j] == ' ') {
					floodFill(visited, i, j, ++currentRoom);
				}
			}
		}
		
		System.out.println("The number of rooms is: " + currentRoom + "\n ____");
		for(char[] a : matrix) {
			System.out.print("|");
			for(char b : a)
				System.out.print(b);
			System.out.println("|");
		}
		System.out.println(" ----\n");
	}
	
	public static void floodFill(boolean[][] visited, int i, int j, int currentRoom) {
		if(!inBounds(i, j) || visited[i][j] || matrix[i][j] == '#')
			return;
		visited[i][j] = true;
		matrix[i][j] = ("" + currentRoom).charAt(0);
		floodFill(visited, i + 1, j, currentRoom);
		floodFill(visited, i - 1, j, currentRoom);
		floodFill(visited, i, j + 1, currentRoom);
		floodFill(visited, i, j - 1, currentRoom);
	}
	
	public static boolean inBounds(int i, int j) {
		return (i >= 0 && j >= 0 && i < matrix.length  && j < matrix[0].length);
	}
}
