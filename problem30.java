/*
Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:

1634 = 1^4 + 6^4 + 3^4 + 4^4
8208 = 8^4 + 2^4 + 0^4 + 8^4
9474 = 9^4 + 4^4 + 7^4 + 4^4
As 1 = 1^4 is not a sum it is not included.

The sum of these numbers is 1634 + 8208 + 9474 = 19316.

Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.
 */
class Problem30 {
  private final static int limit = 1000000;
  private final static int power = 5;
  
  public static int pow(int a, int b) {
    if(a == 0 && b == 0)
      throw new RuntimeException("0^0 is undefined");
    if(a == 0)
      return 0;
    if(b == 0)
      return 1;
    int res = a;
    for(int i = 2; i <= b; ++i)
      res *= a;
    return res;
  }
  
  public static int sumDigitPower(int n, int power) {
    int sum = 0;
    while(n != 0) {
      sum += pow(n%10,power);
      n /= 10;
    }
    return sum;
  }
  
  public static void main(String argv[]) {
    int sum = 0;
    for(int i = 2; i < limit; ++i)
      if(i == sumDigitPower(i,power))
        sum+=i;
    System.out.println(sum);
  }
}
