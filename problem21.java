/*
Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.

For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.

Evaluate the sum of all the amicable numbers under 10000.
 */

class Problem21 {
	// provides the sum of all divisors
	public static int d(int n) {
		if(n == 1)
			return 0;
		int sum = 1; // 1 always divide n
		int limit = n;
		for(int i = 2; i < limit; ++i)
			if(n % i == 0) {
				sum += i;
				limit = n / i;
				if(i != limit)
					sum += limit;
			}
		return sum;
	}
	
	public static void main(String argv[]) {
		final int max = 10000;
		int sumAmicable = 0;
		int a;
		for(int i = 2; i < max; i++){
			a = d(i);
			if(i == d(a) && i != a) {
				System.out.println("Found amicable pair: " + i + "," + a +
													 "," + d(a));
				sumAmicable += i; // count only one of them
			}
		}
		System.out.println(sumAmicable);
	}
}
