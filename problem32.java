/*
We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once; for example, the 5-digit number, 15234, is 1 through 5 pandigital.

The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier, and product is 1 through 9 pandigital.

Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9 pandigital.

HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.
 */
import java.util.LinkedList;

class Problem32 {

  public static int countDigitsProd(int a, int b) {
    return countDigits(a) + countDigits(b) + countDigits(a*b);
  }
  
  public static boolean isPandigital(int a, int b) {
    // assuming it's a nine digits number
    int digits[] = new int[9];
    String str = "" + a + b + a*b; 
    boolean stillPandigital = str.indexOf('0') == -1;
    for(int i = 1; stillPandigital && i <= 9; ++i)
      stillPandigital = str.indexOf(i+'0') != -1;
    return stillPandigital;
  }
  
  public static int countDigits(int a) {
     int count = 0;
     do{
      count++;
      a /= 10;
     }while(a != 0);
     return count;
  }
  
  public static void main(String argv[]) {
    LinkedList<Integer> v = new LinkedList<>();
    int sum = 0, prodDigits;
    for(int a = 1; a <= 10000; ++a){
      boolean cont = true;
      for(int b = 1; cont && b <= 10000 ; b++) {
        prodDigits = countDigitsProd(a,b);
        cont = prodDigits <= 9;
        if(prodDigits == 9 && isPandigital(a,b) && ! v.contains(a*b)){
          sum+=a*b;
          v.add(a*b);
          System.out.println("Founded pandigital: " + a + "*" + b + "=" + a*b);
        }
      }
      
    }
    System.out.println(sum);
  }
}
