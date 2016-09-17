package assignment2;

public class Entry {
	public String guess;
	public int blackPegCount;
	public int whitePegCount;
	
	Entry(String input, int black, int white) {
		guess = input;
		blackPegCount = black;
		whitePegCount = white;
	}
}
