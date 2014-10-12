/*
	
 */

import java.io.*;
import java.util.*;

class Problem22 {
	private static ArrayList<String> names = new ArrayList<>(0);
	private static final String NAME = "names.txt";
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
	public static void main(String argv[]){
		readFile();
		Collections.sort(names);
		long total = 0;
		for(int i = 0; i < names.size(); ++i)
			total += (i+1) * computeStringScore(names.get(i));
		System.out.println(total);
	}
}
