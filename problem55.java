/*
If we take 47, reverse and add, 47 + 74 = 121, which is palindromic.

Not all numbers produce palindromes so quickly. For example,

349 + 943 = 1292,
1292 + 2921 = 4213
4213 + 3124 = 7337

That is, 349 took three iterations to arrive at a palindrome.

Although no one has proved it yet, it is thought that some numbers, like 196, never produce a palindrome. A number that never forms a palindrome through the reverse and add process is called a Lychrel number. Due to the theoretical nature of these numbers, and for the purpose of this problem, we shall assume that a number is Lychrel until proven otherwise. In addition you are given that for every number below ten-thousand, it will either (i) become a palindrome in less than fifty iterations, or, (ii) no one, with all the computing power that exists, has managed so far to map it to a palindrome. In fact, 10677 is the first number to be shown to require over fifty iterations before producing a palindrome: 4668731596684224866951378664 (53 iterations, 28-digits).

Surprisingly, there are palindromic numbers that are themselves Lychrel numbers; the first example is 4994.

How many Lychrel numbers are there below ten-thousand?
*/
import java.math.BigInteger;

class Problem55 {
  private static final int ITERATIONS = 50;
  private static final int LIMIT = 10000;
  public static BigInteger reverse(BigInteger n) {
    final BigInteger TEN = new BigInteger("10");
    final BigInteger ONE = BigInteger.ONE;
    final BigInteger ZERO = BigInteger.ZERO;
    BigInteger reversed = BigInteger.ZERO;
    while(n.compareTo(ZERO) > 0) {
      reversed = reversed.multiply(TEN).add(n.mod(TEN));
      n = n.divide(TEN);
    }
    return reversed;
  }
  
  public static boolean isPalindrome(String s) {
    boolean stillPalindrome = true;
    for(int i = 0; stillPalindrome && i < s.length()/2; ++i)
      stillPalindrome = s.charAt(i) == s.charAt(s.length() - 1 - i);
    return stillPalindrome;
  }
  
  public static boolean isLychrel(int n) {
    BigInteger bign = new BigInteger("" + n), tmp;
    boolean palindrome = false;
    for(int i = 1; ! palindrome && i < ITERATIONS; ++i) {
      bign = bign.add(reverse(bign));
      palindrome = isPalindrome(bign.toString());
    }
    return !palindrome;
  }
  
  public static void main(String argv[]) {
    int counter = 0;
    System.out.println(isLychrel(4994));
    for(int i = 1; i < LIMIT; ++i) {
      if(isLychrel(i)) {
        counter++;
      }
    }
    System.out.println(counter);
  }
} 
