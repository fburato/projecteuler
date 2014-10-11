/*
The following iterative sequence is defined for the set of positive integers:

n → n/2 (n is even)
n → 3n + 1 (n is odd)

Using the rule above and starting with 13, we generate the following sequence:

13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.

Which starting number, under one million, produces the longest chain?

NOTE: Once the chain starts the terms are allowed to go above one million.
 */


class Problem14 {
	public static int sequenceLength(long n) {
		long nL = n;
		int currentLength = 1;
		while(nL != 1) {
				nL = nL % 2 == 0 ? nL / 2 : nL*3 +1;
				currentLength++;
		}
		return currentLength;
	}
	
	public static void main(String argv[]) {
		int currentMax = 4, tmp, startingNumber = -1;
		final int bound = 1000000;
		System.out.println(sequenceLength(13));
		for(int j = 2; j < bound; ++j) {
			tmp = sequenceLength(j);
			if(tmp > currentMax){
				currentMax = tmp;
				startingNumber = j;
			}
		}

		System.out.println(startingNumber);
	}
}
