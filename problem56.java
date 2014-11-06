/*
A googol (10^100) is a massive number: one followed by one-hundred zeros; 100^100 is almost unimaginably large: one followed by two-hundred zeros. Despite their size, the sum of the digits in each number is only 1.

Considering natural numbers of the form, a^b, where a, b < 100, what is the maximum digital sum?
*/
import java.math.BigInteger;

class Problem56 {
  public static int digitalSum(BigInteger n) {
    final BigInteger TEN = new BigInteger("10");
    int digitalSum = 0;
    while(n.compareTo(BigInteger.ZERO) > 0) {
      digitalSum = digitalSum + n.mod(TEN).intValue();
      n = n.divide(TEN);
    } 
    return digitalSum;
  }
  
  public static void main(String argv[]) {
    final int LIMIT = 100;
    int maximum = -1;
    for(int i = 1; i < LIMIT; ++i) {
      BigInteger a = new BigInteger("" + i);
      BigInteger product = a;
      for(int b = 1; b < LIMIT; b++) {
        int digitalSum = digitalSum(product);
        if(digitalSum > maximum) {
          maximum = digitalSum;
          System.out.println("digital sum " + i + "^" + b + "=" + maximum);
        }
        product = product.multiply(a);
      }
    }
    System.out.println(maximum);
  }
}
