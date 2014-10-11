/*
The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.

Find the sum of all the primes below two million.
 */

class Problem10 {
	public static boolean isPrime(int n) {
		if(n <= 1)
			return false;
		int maxCheck = (int) Math.sqrt(n);
		for(int i = 2 ; i <= maxCheck ; i++)
			if(n % i == 0)
				return false;
		return true;
	}
	public static void main(String argv[]){
		final int max = 2000000;
		long sum = 0;
		for(int i = 2; i < max; ++i)
			if(isPrime(i))
				sum += i;
		System.out.println(sum);
	}
}
