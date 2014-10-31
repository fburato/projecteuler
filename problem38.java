/*
Take the number 192 and multiply it by each of 1, 2, and 3:

192 × 1 = 192
192 × 2 = 384
192 × 3 = 576
By concatenating each product we get the 1 to 9 pandigital, 192384576. We will call 192384576 the concatenated product of 192 and (1,2,3)

The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4, and 5, giving the pandigital, 918273645, which is the concatenated product of 9 and (1,2,3,4,5).

What is the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an integer with (1,2, ... , n) where n > 1?
*/

import java.util.Arrays;

class Problem38 {
  public static boolean isPandigital(String s) {
    if(s.length()!=9) return false;
    char[] chars = s.toCharArray();
    Arrays.sort(chars);
    char nextChar = '1';
    boolean stillPandigital = true;
    for(int i = 0; stillPandigital && i < chars.length; ++i) {
      stillPandigital = nextChar == chars[i];
      nextChar = (char) (nextChar + 1);
    }
    return stillPandigital;
  }
  
  public static String getConcatenatedProduct(int starting, int n) {
    String res = "";
    for(int i = 1; i <= n; ++i)
      res = res + (i*starting);
    return res;
  }
  
  public static int getMin(int n) {
    int res = 1;
    for(int i = 1; i < n; ++i)
      res = res * 10;
    return res;
  }
  
  public static int getMax(int n) {
    int res = 9;
    for(int i = 1; i < n ; ++i)
      res = res *10 +9;
    return res;
  }
  
  public static void main(String argv[]) {
    String max = "";
    for(int n = 2; n <= 8; n++) {
      int limit = getMax(9/n+1);
      System.out.println("Newval: " + n + "->" + getMin(9/n) + " , " + limit);
      for(int i = getMin(9/n); i <= limit ;++i) {
        String concat = getConcatenatedProduct(i,n);
        if(isPandigital(concat)){
          System.out.println(i + " " + n + "->" + concat);
          if(max.compareTo(concat) < 0) {
            max = concat;
          }
        }
      }
    }
    System.out.println(max);
  }
}
