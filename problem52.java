/*
It can be seen that the number, 125874, and its double, 251748, contain exactly the same digits, but in a different order.

Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.
*/

class Problem52 {
  public static boolean sameChars(String s1, String s2) {
    if(s1.length() != s2.length())
      return false;
    boolean stillSame = true;
    for(int i = 0 ; stillSame && i < s1.length(); ++i) {
      boolean found = false;
      for(int j = 0; !found && j < s1.length(); ++j) {
        found = s1.charAt(i) == s1.charAt(j);
      }
      stillSame = found;
      found =false;
      for(int j = 0; stillSame && !found && j < s1.length();++j) {
        found = s2.charAt(i) == s1.charAt(j);
      }
      stillSame = stillSame && found;
    }
    return stillSame;
  }
  
  public static void main(String argv[]) {
    int base = 10;
    boolean end = false;
    while(!end) {
      int n;
      String s;
      for(int i = 0; i < base; ++i) {
        n = base +i;
        s = "" + n;
        if(sameChars(s,"" + (6*n)) && sameChars(s,"" + (5*n)) && sameChars(s,"" + (4*n)) && sameChars(s,"" + (3*n)) && sameChars(s,"" + (2*n))){
          System.out.println(n);
          end = true;  
        }
      }
      base = base *10;
    }
  }
} 
