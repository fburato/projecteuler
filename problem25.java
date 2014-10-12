/*
The Fibonacci sequence is defined by the recurrence relation:

F_{n} = F_{n−1} + F_{n−2}, where F_1 = 1 and F_2 = 1.
Hence the first 12 terms will be:

F_1 = 1
F_2 = 1
F_3 = 2
F_4 = 3
F_5 = 5
F_6 = 8
F_7 = 13
F_8 = 21
F_9 = 34
F_10 = 55
F_11 = 89
F_12 = 144
The 12th term, F_12, is the first term to contain three digits.
What is the first term in the Fibonacci sequence to contain 1000 digits?
 */
import java.math.BigInteger;
class Problem25 {
	public static void main(String argv[]) {
		BigInteger a = new BigInteger("1"), b = a , c;
		boolean stop = false;
		int i = 2;
		final int digits = 1000;
		while(!stop) {
			c = b;
			b = a.add(b);
			a = c;
			if(b.toString().length()>=digits) {
				stop = true;
			}
			i++;
		}
		System.out.println("Term: " + i + " is "+ b + " and has " + b.toString().length() + " digits");
	}
}
