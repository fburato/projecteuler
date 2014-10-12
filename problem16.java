/*
2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.

What is the sum of the digits of the number 2^1000?
 */

import java.math.BigInteger;

class Problem16 {
	public static void main(String argv[]) {
		BigInteger n1 = new BigInteger("1024"); //2^10
		n1 = n1.pow(100); //2^1000
		String rap = n1.toString();
		long result = 0;
		for(int i = 0; i < rap.length(); ++i)
			result += rap.charAt(i) - '0';
		System.out.println(result);
	}
}
