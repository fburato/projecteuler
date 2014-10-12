/*
A perfect number is a number for which the sum of its proper divisors is exactly equal to the number. For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.

A number n is called deficient if the sum of its proper divisors is less than n and it is called abundant if this sum exceeds n.

As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be written as the sum of two abundant numbers is 24. By mathematical analysis, it can be shown that all integers greater than 28123 can be written as the sum of two abundant numbers. However, this upper limit cannot be reduced any further by analysis even though it is known that the greatest number that cannot be expressed as the sum of two abundant numbers is less than this limit.

Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
 */

import java.util.*;

class Problem23 {
	private static ArrayList<Integer> abundants = new ArrayList<>();
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
	public static void populateAbundants(int limit) {
		for(int i = 1; i < limit; i++)
			if(i < d(i)) // i is abundant
				abundants.add(i);
	}
	
	public static void main(String argv[]) {
		final int limit = 28124;
		populateAbundants(limit);
		System.out.println("Abundant numbers: " + abundants.size());
		// create the set of numbers that can be written as sum of two abundants numbers
		TreeSet<Integer> sumAbundants = new TreeSet<>();
		for(int i : abundants)
			for(int j : abundants)
				sumAbundants.add(i+j);
		System.out.println("Sum of abundants: "+ sumAbundants.size());
		// compute the sum of not abundants
		long sum = 0;
		for(int i = 1; i < limit; ++i)
			sum += sumAbundants.contains(i) ? 0 : i;
		System.out.println(sum);
	}
}
