// Driver.java 
/*
 * EE422C Project 2 (Mastermind) submission by
 * Rebecca Ho
 * rh29645
 * Slip days used: 0
 * Fall 2016
 */

package assignment2;

import java.util.*;

public class Driver {

	public static void main(String [] args) { 
		boolean testMode = false;
		
		if(args.length > 0){
			if(args[0].equals("1")){ // test mode enabled
				testMode = true;
			}
		}
		
		
		System.out.print("Welcome to Mastermind.  Here are the rules.\n\nThis is a text version of the "
				+ "classic board game Mastermind.The computer will think of a secret code. The code "
				+ "consists of 4 colored pegs.\nThe pegs MUST be one of six colors: blue, green, "
				+ "orange, purple, red, or yellow. A color may appear more than once in the code. "
				+ "You try to guess what colored pegs are in the code and what order they are in.   "
				+ "After you make a valid guess the result (feedback) will be displayed.\nThe result "
				+ "consists of a black peg for each peg you have guessed exactly correct (color "
				+ "and position) in your guess.  For each peg in the guess that is the correct "
				+ "color, but is out of position, you get a white peg.  For each peg that is fully "
				+ "incorrect, you get no feedback.\nOnly the first letter of the color is displayed."
				+ " B for Blue, R for Red, and so forth.\nWhen entering guesses you only need to enter "
				+ "the first character of each color as a capital letter.\n\nYou have 12 guesses to "
				+ "figure out the secret code or you lose the game.  Are you ready to play? (Y/N): ");
		
		Scanner keyboard = new Scanner(System.in);
		String guess = keyboard.nextLine();		
		
		while(guess.equals("Y")){ // continue game if player enters "Y"
			Game mastermind = new Game(testMode);
			mastermind.runGame(keyboard);
			
			System.out.print("\nAre you ready for another game? (Y/N): ");
			guess = keyboard.nextLine();
		}
		

	}
	
}
