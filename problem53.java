/*
There are exactly ten ways of selecting three from five, 12345:

123, 124, 125, 134, 135, 145, 234, 235, 245, and 345

In combinatorics, we use the notation, 5C3 = 10.

In general,

	
        n!
nCr =--------
     r!(n−r)!
     
,where r ≤ n, n! = n×(n−1)×...×3×2×1, and 0! = 1.
It is not until n = 23, that a value exceeds one-million: 23C10 = 1144066.

How many, not necessarily distinct, values of  nCr, for 1 ≤ n ≤ 100, are greater than one-million?

*/

class Problem53{ 
  public static  int smartCombination(int n, int r) {
    int factors[] = new int[r];
    int product = 1;
    for(int i = 0 ; i < r; ++i)
      factors[i] = n-i;
    for(int i = r; i >= 2 ; i--){
      int val = i;
      while(val != 1) {
        int j;
        boolean found = false;
        for(j = 0; !found && j < r ; ++j)
          if(gcd(factors[j],val) != 1)
            found = true;
        j--;
        int gcd = gcd(factors[j],val);
        factors[j] = factors[j] / gcd;
        val = val / gcd;
      }
    }
    for(int i = 0; i < r ;++i)
      product = product * factors[i];
    return product;
  } 
  
  public static int gcd(int a, int b) {
    int c;
    while(b != 0) {
      c = b;
      b = a % b;
      a = c;
    }
    return a;
  }
  
  public static void printArr(int v[]) {
    String res = "", sep = "[ ";
    for(int i = 0; i < v.length; ++i) {
      res = res + sep + v[i];
      sep = " , ";
    }
    System.out.println(res + " ]");
  }
  public static void main(String argv[]) {
    int counter = 0;
    for(int n = 1; n <= 100; ++n) {
      boolean firstMillion = false;
      int r;
      for(r = 1; !firstMillion && r <= n/2+1 ;++r)
        firstMillion = smartCombination(n,r) >= 1000000;
      if(firstMillion) {
        --r;
        counter = counter + n - 2*r +1;
      }
    }
    System.out.println(counter);
  }
}
