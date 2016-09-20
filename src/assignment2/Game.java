// Game.java 
/*
 * EE422C Project 2 (Mastermind) submission by
 * Rebecca Ho
 * rh29645
 * Slip days used: 0
 * Fall 2016
 */

package assignment2;

import java.util.Scanner;

public class Game {

	public Entry[] history = new Entry[GameConfiguration.guessNumber]; // history of guesses	
	private PegCode secretCode;
	private PegCode guess;
	private int guessesLeft = GameConfiguration.guessNumber;
	private int numTurns = 0;
	
	/**
	  * This constructor populates the secretCode attribute of the Game object. It
	  * also begins the game by calling getGuess.
	  */
	Game(boolean testMode) {
		secretCode = new PegCode(); // will give Game object a secret code
		System.out.print("\nGenerating secret code ....");
		if(testMode) {
			System.out.print(" (for this example the secret code is \n");
			secretCode.print();
			System.out.print(")\n");
		} else {
			System.out.print("\n");
		}
	}
	
	/**
	  * This method receives the player's guesses from the keyboard and calls functions to check
	  * the validity of those guesses, check how many pegs the guess generated, and store the guess
	  * in history.
	  * @param keyboard is a Scanner object that can take input from a keyboard.
	  */
	public void runGame(Scanner keyboard) {
		while(guessesLeft > 0) { // continues until out of guesses or player wins
			System.out.print("\nYou have " + guessesLeft + " guesses left.\nWhat is your "
					+ "next guess?\nType in the characters for your guess and press enter.\n"
					+ "Enter guess: ");
			String input = keyboard.nextLine(); // String object of player's guess
			
			while(!checkValidity(input)) { // while guess is not valid, ask for new guess
				System.out.print("\nWhat is your next guess?\nType in the characters for"
						+ " your guess and press enter.\nEnter guess: ");
				input = keyboard.nextLine();
			}
			
			guess = new PegCode(input); // PegCode object of player's guess
			int blackPegCount = checkBlackPegs(guess);
			if(blackPegCount == GameConfiguration.pegNumber) { // guessed correctly, game is over
				System.out.print(" -> Result:  " + blackPegCount + " black pegs - You win!!\n" );
				guessesLeft = 0;
			}
			else { // did not guess correctly, game continues
				int whitePegCount = checkWhitePegs(guess);
				
				System.out.print(" -> Result:  ");
				if(guessesLeft == 1) {
					System.out.print("(Sorry, you are out of guesses. You lose, boo-hoo.)\n");
				} else {
					printPegCounts(blackPegCount, whitePegCount);
					addToHistory(input, blackPegCount, whitePegCount);
			
					for(int i = 0; i < GameConfiguration.pegNumber; i++) { // reset Peg.generatedPeg
						secretCode.pegs[i].reset();
					}
				}
				guessesLeft -= 1;
				numTurns += 1;
				
			}
		}
	}
	
	/**
	  * This method checks whether a player's input is valid for the game. Invalid input consists of
	  * a String object that is not the right size or contains characters that are not included in
	  * the game's colors.
	  * @param input is the String to be checked.
	  * @return true if input is valid, false otherwise.
	  */
	private boolean checkValidity(String input) {
		if(input.equals("HISTORY")) { // output the game's history
			outputHistory();
			return false;
		}
		
		if(input.equals("")) { // if no text/spaces, need 4 blank spaces in front of arrow
			System.out.print("    ");
		}
		System.out.print(input);

		if(input.length() != GameConfiguration.pegNumber) { // input is improper length
			System.out.print(" -> INVALID GUESS\n");
			return false; 
		}
		for(int i = 0; i < GameConfiguration.pegNumber; i++) { // iterate through each peg in the input
			boolean isEqual = false;
			String inputColor = Character.toString(input.charAt(i));
			for(int j = 0; j < GameConfiguration.colors.length; j++) { // iterate through each color
				if (inputColor.equals(GameConfiguration.colors[j])) { // peg matches a color
					isEqual = true;
					break; // break if there is a match
				}
			}
			if(!isEqual) { // peg in the guess did not match a color
				System.out.print(" -> INVALID GUESS\n");
				return false;
			}
		}
		return true;
	}
	
	/**
	  * This method counts how many black pegs the player's guess generates.
	  * @param guess is the PegCode object that contains the player's guess.
	  * @return the number of black pegs generated.
	  */
	private int checkBlackPegs(PegCode guess) {
		int blackPegCount = 0;
		
		for(int i = 0; i < GameConfiguration.pegNumber; i++) { // iterate through all guess pegs
			String guessColor = guess.pegs[i].color;
			String secretColor = secretCode.pegs[i].color;
			
			if(guessColor.equals(secretColor) && !secretCode.pegs[i].generatedPeg && !guess.pegs[i].generatedPeg) { 
				// colors match, secretCode peg and guess peg have not yet generated a black peg
					secretCode.pegs[i].markAsUsed();
					guess.pegs[i].markAsUsed();
					blackPegCount++;	
			}
		}
		return blackPegCount;
	}
	
	/**
	  * This method counts how many white pegs the player's guess generates.
	  * @param guess is the PegCode object that contains the player's guess.
	  * @return the number of white pegs generated.
	  */
	private int checkWhitePegs(PegCode guess) {
		int whitePegCount = 0;
		
		for(int i = 0; i < GameConfiguration.pegNumber; i++) { // iterate through all guess pegs
			String guessColor = guess.pegs[i].color;
			
			if(!guess.pegs[i].generatedPeg){ // guess peg has not already generated a peg
				for(int j = 0; j < GameConfiguration.pegNumber; j++) { // iterate through all secretCode pegs
					if(guessColor.equals(secretCode.pegs[j].color) && !secretCode.pegs[j].generatedPeg) { // colors match and secretCode peg has not yet generated a peg
						secretCode.pegs[j].markAsUsed();
						whitePegCount++;
						break;
					}
				}
			}
		}
		return whitePegCount;
	}
	
	/**
	  * This method prints the number of black and white pegs generated from a player's guess.
	  * @param black is the number of black pegs generated.
	  * @param white is the number of white pegs generated.
	  */
	private void printPegCounts(int black, int white) {
		if(black == 0 && white == 0) { // no pegs
			System.out.print("No pegs\n");
			return;
		}
		
		if(black > 0) { // print black peg number
			System.out.print(black + " black peg");
			if(black > 1) {
				System.out.print("s");
			}
			if(white != 0) {
				System.out.print(", ");
			}
		}
		if(white > 0) { // print white peg number
			System.out.print(white + " white peg");
			if(white > 1) {
				System.out.print("s");
			}
		}
		
		System.out.print("\n");
	}

	/**
	  * This method adds an Entry to the game's history for the current turn.
	  * @param input is the player's guess.
	  * @param black is the number of black pegs that were generated.
	  * @param white is the number of white pegs that were generated.
	  */
	private void addToHistory(String input, int black, int white) {
		history[numTurns] = new Entry(input, black, white);
	}
	
	/**
	  * This method prints the game's history.
	  */
	private void outputHistory() {
		if(numTurns == 0) {
			return; // print nothing if the player has not made any valid guesses
		} else {
			for(int i = 0; i < numTurns; i++) {
				System.out.print(history[i].guess + "		" + history[i].blackPegCount +
						"B_" + history[i].whitePegCount + "W\n");
			}
		}
	}
	
}
