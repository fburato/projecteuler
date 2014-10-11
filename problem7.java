/*
By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.

What is the 10 001st prime number?
 */

class Problem7 {
	public static boolean isPrime(int n) {
		if(n <= 1)
			return false;
		final int maxCheck = (int) Math.sqrt(n);
		for(int i = 2; i <= maxCheck ; ++i)
			if(n % i == 0)
				return false;
		return true;
	}
	public static void main(String argv[]) {
		int countedPrimes = 0, lastPrime = -1;
		final int numPrimes = 10001;
		int currentNum=2;
		while(countedPrimes != numPrimes){
			if(isPrime(currentNum)){
				countedPrimes++;
				lastPrime = currentNum;
			}
			currentNum++;
		}
		System.out.println(lastPrime);
	}
}
