/*
The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves prime.

There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.

How many circular primes are there below one million?
 */

class Problem35 {
  private static final int limit = 1000000; 
  public static boolean isPrime(int n) {
    if(n <=1)
      return false;
    int limit = (int) Math.sqrt(n);
    boolean stillPrime = true;
    for(int i = 2; stillPrime && i <= limit; ++i)
      stillPrime = n % i != 0;
    return stillPrime;
  }
  
  public static  boolean[] sieve(int limit) {
    boolean[] res = new boolean[limit];
    res[0] = false;
    res[1] = false;
    for(int i = 2; i < limit; ++i) {
      if(isPrime(i)) {
        res[i] = true;
        for(int j = 2; j*i < limit; j++)
          res[i*j] = false;
      }
    }
    return res;
  }
  
  public static int getVal(String s) {
    int total = 0;
    for(int i = 0 ; i < s.length(); ++i)
      total = total * 10 + s.charAt(i) - '0';
    return total;
  }
  
  public static String nextRotation(String s) {
    if(s.length() == 1)
      return s;
    else 
      return s.substring(1,s.length()) + s.charAt(0);
  }
  
  
  public static boolean isCircular(int n, boolean[] sieve) {
    boolean isCircular = sieve[n];
    String starting = "" + n;
    String nextRot = nextRotation(starting);
    while(isCircular && !nextRot.equals(starting)) {
      isCircular = sieve[getVal(nextRot)];
      nextRot = nextRotation(nextRot);
    }
    return isCircular;
  }
  
  public static void main(String argv[]) {
    boolean sieve[] = sieve(limit);
    int counter = 0;
    for(int i = 2 ; i < limit ; i++)
      if(isCircular(i,sieve)) {
        System.out.println(i);
        counter++;
      }
    System.out.println(counter);
  }
}
