/*
The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in two ways: (i) each of the three terms are prime, and, (ii) each of the 4-digit numbers are permutations of one another.

There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property, but there is one other 4-digit increasing sequence.

What 12-digit number do you form by concatenating the three terms in this sequence?
*/
import java.util.Arrays;

class Permutation {
  private int[] initial;
  public Permutation(int init[]) {
    initial = Arrays.copyOf(init,init.length);
  }
  
  public void setInitial(int init[]) {
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

class Problem49 {
  public static boolean isPrime(int n) {
    if(n <= 1)
      return false;
    int limit = (int) Math.sqrt(n);
    boolean stillPrime = true;
    for(int i = 2; stillPrime && i <= limit; ++i)
      stillPrime = n % i != 0;
    return stillPrime;
  }
  
  private static int countDigits(int n) {
    int counter = 1;
    while(n > 0) {
      n = n / 10;
      counter++;
    }
    return counter;
  }
  
  public static int[] getDigits(int n) {
    int[] res = new int[countDigits(n)];
    for(int i = res.length -1; i>=0; --i){
      res[i] = n % 10;
      n = n / 10;
    }
    return res;
  }
  
  public static boolean arePermutation(String s1, String s2) {
    if(s1.length() != s2.length())
      return false;
    boolean stillPermutation = true;
    for(int i = 0; stillPermutation && i < s1.length(); ++i) {
      boolean found = false;
      for(int j = 0; !found && j < s1.length(); ++j)
        found = s1.charAt(i) == s2.charAt(j);
      stillPermutation = found;
      found = false;
      for(int j = 0; stillPermutation && !found && j < s1.length(); ++j)
        found = s2.charAt(i) == s1.charAt(j);
      stillPermutation = stillPermutation && found;
    }
    return stillPermutation;
  }
  
  public static int getVal(int v[]) {
    int sum = 0;
    for(int i = 0; i < v.length; ++i)
      sum = sum*10 + v[i];
    return sum;
  }
  
  public static void main(String argv[]) {
    int dummy[] = {0};
    Permutation p = new Permutation(dummy);
    for(int i = 1000; i <= 9999; ++i) {
      if(isPrime(i) && i != 1487) {
        int startingArr[] = getDigits(i); 
        p.setInitial(startingArr);
        for(int j = 1; j <= 24 ;++j) {
          int permutval = getVal(p.getPermutation(j));
          if(permutval > i && isPrime(permutval)) {
            int increment = permutval - i;
            int secondval = permutval + increment;
            if(secondval <= 9999 &&
                isPrime(secondval) &&
                arePermutation("" + i , "" + secondval)) {
              System.out.println("" + i + permutval + secondval);
              return ;   
            }
          }
        }
      }
    }
  }
}
