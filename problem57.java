/*
It is possible to show that the square root of two can be expressed as an infinite continued fraction.

√ 2 = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...

By expanding this for the first four iterations, we get:

1 + 1/2 = 3/2 = 1.5
1 + 1/(2 + 1/2) = 7/5 = 1.4
1 + 1/(2 + 1/(2 + 1/2)) = 17/12 = 1.41666...
1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...

The next three expansions are 99/70, 239/169, and 577/408, but the eighth expansion, 1393/985, is the first example where the number of digits in the numerator exceeds the number of digits in the denominator.

In the first one-thousand expansions, how many fractions contain a numerator with more digits than denominator?
*/

import java.math.BigInteger;

class Problem57 {
  public static int digits(BigInteger n) {
    return n.toString().length();
  }
  
  public static void main(String argv[]) {
    final int LIMIT = 1000;
    int counter = 0;
    BigInteger a = new BigInteger("2");
    final BigInteger TWO = a;
    BigInteger b = BigInteger.ONE;
    BigInteger c,d,num,den;
    for(int i = 2 ; i <= LIMIT; ++i) {
      c= TWO.multiply(a).add(b);
      d= a;
      num = c.add(d);
      den = c;
      if(digits(num) > digits(den)) {
        counter++;
        //System.out.println("Iteration " + i + ": " + num + "/" + den);
      }
      a=c;
      b=d;
    }
    System.out.println(counter);
  }
}
