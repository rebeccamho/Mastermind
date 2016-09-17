// Peg.java 
/*
 * EE422C Project 2 (Mastermind) submission by
 * Rebecca Ho
 * rh29645
 * Slip days used: 0
 * Fall 2016
 */

package assignment2;

public class Peg {
	public String color;
	boolean generatedPeg = false; // true if peg has generated a black or white peg
	
	/**
	  * This constructor sets the object's color to the input String.
	  * @param color is the String that the object's color is set to.
	  */
	Peg(String color) {
		this.color = color;
	}
	
	void markAsUsed() {
		generatedPeg = true;
	}
	
	void reset() {
		generatedPeg = false;
	}
}
