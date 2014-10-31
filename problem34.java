/*
145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.

Find the sum of all numbers which are equal to the sum of the factorial of their digits.

Note: as 1! = 1 and 2! = 2 are not sums they are not included.
*/

class Problem34 {
  public static  int factorial(int i) {
    if(i <= 1)
      return 1;
    int total = 1;
    for(int j = 2; j <= i; ++j)
      total *= j;
    return total;
  }
  /* 
  the upper bound in the number of digits is the first s.t.
  n*9! > 10^n -> 9! > 10^n/n which is 7 because
  9! = 362880
  10^6 / 6 = 166666
  10^7 / 7 = 1428571
  */
  
  public static int sumFactDigit(int n) {
    int sum = 0;
    while(n > 0){
      sum += factorial(n % 10);
      n /= 10;
    }
    return sum;
  }
  
  public static void main(String argv[]) {
    final int limit = 9999999;
    int sum = 0;
    for(int i = 3; i <= limit; ++i)
      if(sumFactDigit(i) == i){
        System.out.println(i);
        sum+=i;
      }
    System.out.println("Final result= " + sum);
  }
  
}
