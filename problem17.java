/*
If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.

If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?


NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters and 115 (one hundred and fifteen) contains 20 letters. The use of "and" when writing out numbers is in compliance with British usage.
 */

class Problem17 {
	// array for units and teens
	private static int[] unitsTeens = {3,3,5,4,4,3,5,5,4,3,6,6,8,8,7,7,9,8,8};
	// array for the tens
	private static int[] tens = {0,6,6,5,5,5,7,6,6};
	private static int hundred = 7;

	public static int computeLetters(int i) {
		int sum = 0;
		int decs, hund, thous;
		decs = (i / 10) % 10;
		hund = (i / 100) % 10;
		thous = (i / 1000) % 10;
		if(thous == 1) sum += 11;
		if(hund > 0) {
			sum += unitsTeens[hund-1] + hundred;
			if(i % 100 != 0) sum += 3; //and treatment
		}
		if(decs > 1) {
			sum += tens[decs-1];
			if(i%10!=0) sum += unitsTeens[(i%10)-1];
		} else {
			// special case: we are in the teens region
			if(i%100 != 0) sum += unitsTeens[(i % 100) -1];
		}
		return sum;
	} 
	
	public static void main(String argv[]) {
		final int limit = 1000;
		int sum = 0;
		for(int i = 1; i <=limit ; ++i) {
			sum += computeLetters(i);
		}
		System.out.println(sum);
	}
}
