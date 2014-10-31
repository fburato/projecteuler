/*
The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in some order, but it also has a rather interesting sub-string divisibility property.

Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:

d2d3d4=406 is divisible by 2
d3d4d5=063 is divisible by 3
d4d5d6=635 is divisible by 5
d5d6d7=357 is divisible by 7
d6d7d8=572 is divisible by 11
d7d8d9=728 is divisible by 13
d8d9d10=289 is divisible by 17
Find the sum of all 0 to 9 pandigital numbers with this property.
*/

import java.util.Arrays;

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

class Problem43 {
  private static long getVal(String string, int s, int e) {
    long total = 0;
    for(int i = s; i < e ; ++i)
      total = total* 10 + string.charAt(i) - '0';
    return total;
  }
  
  public static boolean verifProperty(String s) {
    int a = (int) getVal(s,1,4);
    int b = (int) getVal(s,2,5);
    int c = (int) getVal(s,3,6);
    int d = (int) getVal(s,4,7);
    int e = (int) getVal(s,5,8);
    int f = (int) getVal(s,6,9);
    int g = (int) getVal(s,7,10);
    return a % 2 == 0 && b % 3 == 0 && c % 5 == 0 && d % 7 == 0
        && e % 11 == 0 && f % 13 == 0 && g % 17 == 0;
  }
  
  public static void main(String argv[]) {
    int arr[] = {0,1,2,3,4,5,6,7,8,9};
    Permutation p = new Permutation(arr);
    long sum = 0;
    for(int i = 9*8*7*6*5*4*3*2+1; i < 10*9*8*7*6*5*4*3*2; ++i){
      String number = p.arrToString(p.getPermutation(i));
      if(verifProperty(number)) {
        System.out.println(number);
        sum += getVal(number,0,number.length());
      }
    }
    System.out.println(sum);
  }
}
