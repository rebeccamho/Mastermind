// Driver.java 
/*
 * EE422C Project 2 (Mastermind) submission by
 * Rebecca Ho
 * rh29645
 * Slip days used: 0
 * Fall 2016
 */

package assignment2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Driver {

	// NEED TO ADD: testing mode
	public static void main(String [] args) { 
		System.out.print("Are you ready to play? (Y/N): ");
		Scanner keyboard = new Scanner(System.in);
		String guess = keyboard.nextLine();		
		
		while(guess.equals("Y")){
			Game mastermind = new Game(keyboard);
			System.out.print("\nAre you ready for another game? (Y/N): ");
			guess = keyboard.nextLine();
		}
		
		
		
		

		//System.out.println(guess);
//		keyboard.close();
	}
	
}
