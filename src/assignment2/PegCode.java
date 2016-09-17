// SecretCode.java 
/*
 * EE422C Project 2 (Mastermind) submission by
 * Rebecca Ho
 * rh29645
 * Slip days used: 0
 * Fall 2016
 */

package assignment2;

public class PegCode {
	public Peg[] pegs = new Peg[GameConfiguration.pegNumber];
	public int length = 0;
	
	
	/**
	  * This constructor sets the object's pegs attribute equal to a generated String array.
	  */
	PegCode() {
		String code = SecretCodeGenerator.getInstance().getNewSecretCode();
		length = GameConfiguration.pegNumber;
		int i = 0;
		for(i = 0; i < GameConfiguration.pegNumber; i++) { // make each letter in code a peg
			pegs[i] = new Peg(Character.toString(code.charAt(i)));
		}
	}
	
	/**
	  * This constructor sets the object's pegs attribute equal to an input String array.
	  * @param code is the array of Strings to be copied into pegs.
	  */
	PegCode(String code) {
		int i = 0;
		length = code.length();
		for(i = 0; i < length; i++) { // make each letter in code a peg
			pegs[i] = new Peg(Character.toString(code.charAt(i)));
		}
	}
	
	/**
	  * This method prints the colors of the pegs that comprise the PegCode.
	  */
	public void print() {
		for(int i = 0; i < length; i++){ 
			System.out.print(pegs[i].color);
		} 
	}
}
