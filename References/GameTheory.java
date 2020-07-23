package References;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class GameTheory {
	
	/*
	 * Nim game: a game where there are n piles of rocks, each with a different number of rocks.
	 * On their turn, players can remove 1 or more rocks from a single pile.
	 * Whoever cannot make a move (no rocks left) loses.
	 * 
	 * Game theory specifies that if both players play optimally, the winner depends solely on 
	 * the state of the game.
	 */
	public static String nim(List<Integer> piles) {
		/* Start with these two provable facts(theorems?):
		 * 		1. If the XOR sum of the n piles is already 0, there is no way to make the XOR sum 0 by removing k rocks, no matter what k is.
		 * 		2. If the XOR sum of the n piles is not 0, there is a way to make the XOR sum 0 by removing k rocks, where k is some number.
		 */
		
		/*
		 * In the course of the game:
		 * 1. 
		 *  - if it is your turn and the XOR sum is 0, anything you pick will make the XOR sum non-zero.
		 *  - Then, the opponent can choose some number k to make the XOR sum 0 again.
		 *  - Thus, you will ALWAYS be left with an XOR sum of 0. This includes sum(n) = 0, the losing state. YOU LOSE
		 *  
		 *  2. 
		 * 	- if it is your turn and the XOR sum is not 0, you can force it to be zero.
		 * 	- Then, the opponent will ALWAYS be left with an XOR sum of 0, including the losing state. YOU WIN
		 */
		
		int nimSum = piles.get(0);
		for(int i = 1; i < piles.size(); i++) {
			nimSum ^= piles.get(i);
		}
		
		if(nimSum == 0) {
			// whoever starts loses
			return "Player B";
		} else {
			// whoever starts wins
			return "Player A";
		}
	}
	
	
	
	/*
	 * Sprague-Grundy theorem
	 * 
	 * An impartial game is one where all players can make the same moves in a certain state.
	 * For example, chess is not an impartial game b/c player A can only move white pieces.
	 * 
	 * ANY position of an impartial game is equivalent to a nim pile of a certain size.
	 * Of course, finding that nim pile is sometimes complicated.
	 */
	
	
	/*
	 * Nim game, but players are given the option to add k rocks to any pile, in addition to removing some number of rocks.
	 */
	public static String additionNim(List<Integer> piles) {
		
		/*
		 * The solution does not change!
		 * Assume that player A is in a winning state:
		 * 	- Player A should play normally.
		 * 	- If player B adds some k rocks to a pile, player A's next move is just to remove that number of rocks. 
		 * 	  This returns it to a winning state.
		 * 
		 * Note that rules would have to be implemented to prevent infinite loops.
		 */
		
		int nimSum = piles.get(0);
		for(int i = 1; i < piles.size(); i++) {
			nimSum ^= piles.get(i);
		}
		
		if(nimSum == 0) {
			// whoever starts loses
			return "Player B";
		} else {
			// whoever starts wins
			return "Player A";
		}
	}
	
	
	/*
	 * Nim game, where instead of taking from a pile and discarding,
	 * players take from step n and move it to step n-1.
	 * Assume the bottom step is step 0.
	 */
	public static String stairCaseNim(List<Integer> steps) {
		/*
		 * Some observations:
		 * 	- If the losing state is all items on step 0, then moving to step 0 is equivalent to discarding the items.
		 * 	
		 * The optimal strategy is to play Nim on only the odd steps. Why?
		 * Assume Player A is in a winning position (XOR sum of all odd steps == 0 after their turn)
		 * 
		 * 	- Player B tries to move rocks from an even step to an odd step. This is equivalent to adding rocks to a pile.
		 * 	  As discussed, this does nothing -- those same rocks can again be moved to a lower step or discarded the next turn.
		 * 
		 * 	- Player B tries to move rocks from an odd step to an even step. This is equivalent to a regular nim move.
		 * 	  This move would make the XOR sum of all odd steps non-zero, allowing player A to keep the winning position.
		 */
		
		if(steps.size() > 1) {
			int nimSum = steps.get(1);
			for(int i = 3; i < steps.size(); i+=2) {
				nimSum ^= steps.get(i);
			}
			
			if(nimSum == 0) {
				// whoever starts loses
				return "Player B";
			} else {
				// whoever starts wins
				return "Player A";
			}
		}
		
		// A single step - player A has no moves
		return "Player B";
	}
}
