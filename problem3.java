/*
The prime factors of 13195 are 5, 7, 13 and 29.

What is the largest prime factor of the number 600851475143 ?
                                        9223372036854775807
*/
import java.util.*;

class Problem3 {
	// test whether n is prime
	public static boolean isPrime(long n){
		if(n <= 1)
			return false;
		// count the number of divisors from n to sqrt(n)
		boolean isPrime = true; 
		long maxCheck = n / 2;
		for(long i = 2; isPrime && i <= maxCheck; i++)
			isPrime = n % i != 0;
		return isPrime;
	}
	
	public static void main(String argv[]) {
		long n = Long.decode("600851475143");
		long biggestPrimeDivisor = -1;
		while( n > 1) {
			// find out a new divisor of n
			boolean stop = false;
			long i;
			for(i = 2; !stop && i <= n; i++)
				stop = n % i == 0;
			i -= 1;
			// i divides n
			if( isPrime(i) )
				biggestPrimeDivisor = i;
			n /= i;
		}
		System.out.println(biggestPrimeDivisor);
	}
}
