/*
The sum of the squares of the first ten natural numbers is,

1^2 + 2^2 + ... + 10^2 = 385
The square of the sum of the first ten natural numbers is,

(1 + 2 + ... + 10)^2 = 55^2 = 3025
Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 − 385 = 2640.

Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
*/

class Problem6 {
	public static void main(String argv[] ) {
		int squareofsum = 50*101*50*101;
		int sumofsquare = 0;
		for(int i = 1; i <= 100; ++i)
			sumofsquare += i*i;
		System.out.println(squareofsum - sumofsquare);
	}
}
