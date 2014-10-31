class Problem36 {
  public static boolean isPalindrome(String s){
    boolean stillPalindrome = true;
    for(int i = 0; stillPalindrome && i < s.length() / 2; ++i)
      stillPalindrome = s.charAt(i) == s.charAt(s.length() - 1 -i);
    return stillPalindrome;
  }
  
  public static String getBaseTwo(int n) {
    String s = "";
    do {
      s = "" + (n%2) + s;
      n /= 2;
    } while(n > 0);
    return s;
  }
  
  private static int limit = 1000000;
  
  public static void main(String argv[]) {
    String n,nbase2;
    int sum = 0;
    for(int i = 0; i < limit; ++i) {
      n = "" + i;
      nbase2 = getBaseTwo(i);
      if(isPalindrome(n) && isPalindrome(nbase2)) {
        System.out.println(n + " " + nbase2); 
        sum += i;
      }
    }
    System.out.println(sum);
  } 
}
