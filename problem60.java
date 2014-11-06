/*
The primes 3, 7, 109, and 673, are quite remarkable. By taking any two primes and concatenating them in any order the result will always be prime. For example, taking 7 and 109, both 7109 and 1097 are prime. The sum of these four primes, 792, represents the lowest sum for a set of four primes with this property.

Find the lowest sum for a set of five primes for which any two primes concatenate to produce another prime.
*/

import java.util.LinkedList;
import java.util.Iterator;

class Problem60 {
  public static boolean sieve[];
  public static final int LIMIT = 10000;
  public static final int SET_CARDINALITY = 5;
  static {
    buildSieve();
  }
  
  /* returns true iff n is prime*/
  public static boolean isPrime(long n) {
    if(n <= 1)
      return false;
    long limit = (long) Math.sqrt(n);
    boolean stillPrime = true;
    for(int i = 2 ;stillPrime && i <= limit; ++i) {
      stillPrime = n % i != 0;
    }
    return stillPrime;
  }
  
  /* builds the sieve for primality test */
  public static void buildSieve() {
    sieve = new boolean[LIMIT];
    for(int i = 2; i < LIMIT; ++i)
      sieve[i] = true;
    for(int i = 2; i < LIMIT; ++i) {
      if(sieve[i]) {
        for(int j = 2; j*i < LIMIT; j++) {
          sieve[i*j] = false;
        }
      } 
    }
  }
  
  /* returns true iff prime n can augment set s*/
  public static boolean isAugmentable(LinkedList<Integer> s, int n) {
    boolean isStillAugmentable = true;
    for(Iterator<Integer> it = s.iterator(); isStillAugmentable && it.hasNext(); ) {
      int i = it.next();
      isStillAugmentable = isPairRemarkable(n,i);
    }
    return isStillAugmentable;
  }
  
  /* return true iff the pair of primes p and q is remarkable*/
  public static boolean isPairRemarkable(int p, int q) {
    String s1 = "" + p + q, s2 = "" + q + p;
    int test1 = Integer.decode(s1), test2 = Integer.decode(s2);
    return (test1 < LIMIT && sieve[test1] || isPrime(test1)) &&
        (test2 < LIMIT && sieve[test2] || isPrime(test2));
  }
  
  public static void main(String argv[]) {
    LinkedList<LinkedList<Integer>> sets = new LinkedList<>();
    for(int i = 3; i < LIMIT; ++i) {
      if(sieve[i]) {
        // find out if any of the previous sets is augmentable with i
        for(LinkedList<Integer> s : sets) {
          if(isAugmentable(s,i)) {
            s.addLast(i);
            if(s.size() == SET_CARDINALITY) {
              int sum = 0;
              for(int k : s) {
                System.out.print(k + " ");
                sum = sum +k;
              }
              System.out.println("-> " + sum);
              return ;
            }
          }
        }
        // look for new possible sets
        for(int j = 2; j <= i; ++j) {
          if(sieve[j] && isPairRemarkable(i,j)) {
            LinkedList<Integer> newset = new LinkedList<Integer>();
            newset.addLast(i);
            newset.addLast(j);
            sets.addLast(newset);
          }
        }
      }
    }
  }
  
}
