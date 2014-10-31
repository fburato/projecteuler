/*
The nth term of the sequence of triangle numbers is given by, tn = ½n(n+1); so the first ten triangle numbers are:

1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...

By converting each letter in a word to a number corresponding to its alphabetical position and adding these values we form a word value. For example, the word value for SKY is 19 + 11 + 25 = 55 = t10. If the word value is a triangle number then we shall call the word a triangle word.

Using words.txt (right click and 'Save Link/Target As...'), a 16K text file containing nearly two-thousand common English words, how many are triangle words?
*/

import java.io.*;
import java.util.*;

class Problem42 {
	private static ArrayList<String> names = new ArrayList<>(0);
	private static final String NAME = "p042_words.txt";
	private static void readFile() {
		try {
			InputStreamReader in = new FileReader(NAME);
			int state = 0;
			int lastCharRead = in.read();
			while(lastCharRead != -1) {
				if(state == 0) {
					//reading the first ", going to state 1
					state = 1;
				} else if(state == 1) {
					// read characters from until the next "
					String s = "";
					while(lastCharRead != '"'){
						s = s+ (char) lastCharRead;
						lastCharRead = in.read();
					}
					names.add(s.toUpperCase());
					// read the '"' character
					// read ',' character
					lastCharRead = in.read();
					//next character must be '"'
					state = 0;
				}
				lastCharRead = in.read();
			}
		} catch (Exception e) {
			throw new RuntimeException("Problem reading from file "+NAME+ "\n" + e);
		}
	}
	
	public static int computeStringScore(String s){
		int sum = 0;
		for(int i = 0; i < s.length(); ++i)
			sum += s.charAt(i) -'A'+1;
		return sum;
	}
	
	public static boolean isTriangular(int i) {
	  int t = (int) Math.sqrt(2*i);
	  int triangular = t*(t+1)/2;
	  while(triangular < i){
	    t++;
	    triangular = t*(t+1)/2;
	  }
	  return triangular == i;
	}
	
	public static void main(String argv[]){
		readFile();
		int count = 0;
		for(int i = 0; i < names.size(); ++i) {
			if(isTriangular(computeStringScore(names.get(i)))) {
			  count++;
			}
	  }
		System.out.println(count);
	}
}
