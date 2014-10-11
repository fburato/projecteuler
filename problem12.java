/*
The sequence of triangle numbers is generated by adding the natural numbers. So the 7th triangle number would be 1 + 2 + 3 + 4 + 5 + 6 + 7 = 28. The first ten terms would be:

1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...

Let us list the factors of the first seven triangle numbers:

 1: 1
 3: 1,3
 6: 1,2,3,6
10: 1,2,5,10
15: 1,3,5,15
21: 1,3,7,21
28: 1,2,4,7,14,28
We can see that 28 is the first triangle number to have over five divisors.

What is the value of the first triangle number to have over five hundred divisors?
 */

class Problem12 {

	public static long getTriangular(int n) {
		return ((long)n)*(((long)n)+1)/2;
	}
	
	public static int countDivisors(long n) {
		int divisors = 2;
		long d = 2, top = n;
		for(; d < top ; d++) {
			if(n % d == 0){
				if(n / d != d)
					divisors += 2;
				else
					divisors++;
				top = n / d;
			}
		}
		return divisors;
	}

	
	public static void main(String argv[]) {
		
		for(int i = 0; i < Integer.MAX_VALUE; i++)
			if(countDivisors(getTriangular(i)) >= 500){
				System.out.println(getTriangular(i));
				return ;
			}
	}
}
