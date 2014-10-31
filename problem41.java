/*
We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once. For example, 2143 is a 4-digit pandigital and is also prime.

What is the largest n-digit pandigital prime that exists?
*/

import java.util.Arrays;
import java.math.BigInteger;
class Permutation {
  private int[] initial;
  public Permutation(int init[]) {
    initial = Arrays.copyOf(init,init.length);
  }
  
  private static long factorial(long n) {
    if(n <= 1)
      return 1;
    int total = 1;
    for(long i= 2 ; i <= n; ++i)
      total *= i;
    return total;
  }
  
  private static void fixResidual(int[] v, int i, int j) {
    //set v[i] to v[j]
    int save = v[j];
    for(int k = j; k > i; k--)
      v[k] = v[k-1];
    v[i] = save;
  }
  public int[] getPermutation(long n) {
    int[] residual = Arrays.copyOf(initial,initial.length);
    for(int i = 0; i < residual.length; ++i) {
      long f = factorial(residual.length - i -1);
      int j = 0;
      while(n > 0) {
        n -= f;
        j++;
      }
      n += f;
      j--;
      fixResidual(residual,i,j+i);
    }
    return residual;
  }
  
  public static String arrToString(int v[]) {
    String res = "";
    for(int i = 0; i < v.length; ++i) {
      res = res + v[i];
    }
    return res;
  }
}

class Problem41 {
  public static boolean isPrime(BigInteger n) {
    if(n.compareTo(BigInteger.ONE) <= 0)
      return false;
    BigInteger two = new BigInteger("2");
    BigInteger limit = n.divide(two);
    boolean stillPrime = true;
    for(BigInteger i = two; stillPrime &&  i.compareTo(limit) <= 0; i = i.add(BigInteger.ONE)) {
      stillPrime = n.mod(i).compareTo(BigInteger.ZERO) != 0;
    }
    return stillPrime;
  }
  
  
  public static int factorial(int n) {
    if(n <= 1)
      return 1;
    int prod = 1;
    for(int i = 2; i <= n; ++i)
      prod = prod * i;
    return prod;
  }
  
  public static void main(String argv[]) {
    boolean found = false;
    int arr[];
    for(int n = 9;!found && n >= 3 ; n--) {
      arr = new int[n];
      for(int i = 0; i < n; ++i)
        arr[i] = i+1;
      Permutation p = new Permutation(arr);
      int i;
      for(i = factorial(n); !found && i >= 1; i--) {
        found = isPrime(new BigInteger(p.arrToString(p.getPermutation(i))));
      }
      if(found) {
        System.out.println(p.arrToString(p.getPermutation(i+1)));
      }
    }
  }
}
