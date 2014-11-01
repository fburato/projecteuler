/*
The series, 11 + 22 + 33 + ... + 1010 = 10405071317.

Find the last ten digits of the series, 11 + 22 + 33 + ... + 10001000.
*/
import java.math.BigInteger;
class Problem48 {

  private final static BigInteger eleven = new BigInteger("10000000000");
  public static BigInteger powPow(int n) {
    return powPow(new BigInteger(""+n), n);
  }
  
  private static BigInteger powPow(BigInteger n, int exponent) {
    if(exponent == 0)
      return BigInteger.ONE;
    if(exponent == 1)
      return n;
    BigInteger powHalf = powPow(n,exponent/2);
    if(exponent % 2 == 0)
      return powHalf.multiply(powHalf).mod(eleven);
    else
      return powHalf.multiply(powHalf).mod(eleven).multiply(n).mod(eleven);
  }
  
  public static void main(String argv[]) {
    int limit = 1000;
    BigInteger sum = BigInteger.ZERO;
    BigInteger bigi = BigInteger.ONE;
    for(int i = 1; i <= limit ; i++) {
      sum = sum.add(powPow(bigi,i));
      bigi = bigi.add(BigInteger.ONE);
    }
    System.out.println(sum);
  }
}
