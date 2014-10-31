/*
Euler discovered the remarkable quadratic formula:

n² + n + 41

It turns out that the formula will produce 40 primes for the consecutive values n = 0 to 39. However, when n = 40, 402 + 40 + 41 = 40(40 + 1) + 41 is divisible by 41, and certainly when n = 41, 41² + 41 + 41 is clearly divisible by 41.

The incredible formula  n² − 79n + 1601 was discovered, which produces 80 primes for the consecutive values n = 0 to 79. The product of the coefficients, −79 and 1601, is −126479.

Considering quadratics of the form:

n² + an + b, where |a| < 1000 and |b| < 1000

where |n| is the modulus/absolute value of n
e.g. |11| = 11 and |−4| = 4
Find the product of the coefficients, a and b, for the quadratic expression that produces the maximum number of primes for consecutive values of n, starting with n = 0.
 */
class Problem27 {
  public static boolean isPrime(long n) {
    if(n <=1 )
      return false;
    // n > 2
    boolean stillPrime = true;
    long limit = (int) Math.sqrt(n);
    for(long i = 2; stillPrime && i <= limit ; ++i)
      stillPrime = n % i != 0;
    return stillPrime;
  }
  
  public static boolean[] crivex(int limit) {
    if(limit <= 0)
      return null;
    boolean crivex[] = new boolean[limit];
    for(int i = 0; i < crivex.length; ++i)
      crivex[i] = true;
    crivex[0] = false;
    for(int i = 1; i < limit; ++i)
      if(crivex[i])
        for(int j = 2; j*(i+1) <= limit; ++j)
          crivex[j*(i+1)-1] = false;
    return crivex;
  }
  
  public static void main(String argv[]) {
    final int limit = 1000;
    boolean[] crivex = crivex(limit);
    int maxConsecPrimes = 0, maxA = 0, maxB = -1;
    for(int b = 1; b < limit ;++b)
      if(crivex[b-1])
        for(int a = -limit + 1; a < limit; ++a) {
          long n = 1;
          long p =1+ a + b;
          while( p > 0 && ((p-1 < limit && crivex[(int) (p-1)]) || isPrime(p))) {
            n++;
            p = n*n + a*n +b;
          }
          //System.out.println("For a=" + a +" and b=" + b + " produced " + n  + " consecutives primes");
          if(n > maxConsecPrimes) {
            maxConsecPrimes =(int) n;
            maxA = a;
            maxB = b;
          }
        }
    System.out.println("For a=" + maxA +" and b=" + maxB + " produced " + maxConsecPrimes  + " consecutives primes"); 
  }
}
