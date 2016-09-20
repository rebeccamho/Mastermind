// Entry.java 
/*
 * EE422C Project 2 (Mastermind) submission by
 * Rebecca Ho
 * rh29645
 * Slip days used: 0
 * Fall 2016
 */

package assignment2;

public class Entry {
	public String guess;
	public int blackPegCount;
	public int whitePegCount;
	
	/**
	  * This constructor sets all of the object's attributes.
	  * @param input is the String to set guess to.
	  * @param black is the number to set blackPegCount to.
	  * @param white is the number to set whitePegCount to.
	  */
	Entry(String input, int black, int white) {
		guess = input;
		blackPegCount = black;
		whitePegCount = white;
	}
}
