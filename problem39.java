/*
If p is the perimeter of a right angle triangle with integral length sides, {a,b,c}, there are exactly three solutions for p = 120.

{20,48,52}, {24,45,51}, {30,40,50}

For which value of p ≤ 1000, is the number of solutions maximised?

Formula: given m, n with m>n the triplet (2*m*n, m^2-n^2, m^2+n^2) is  pythagorean
For m-n odd, (m,n) coprime and m>n the triplet is primitive

*/
class Problem39 {
  public static int calcA(int m, int n) {
    return 2*m*n;
  }
  
  public static int calcB(int m, int n) {
    return m*m-n*n;
  }
  
  public static int calcC(int m, int n) {
    return m*m+n*n;
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
  
  public static boolean coprime(int a, int b) {
    return gcd(a,b) == 1;
  }
  
  public static void main(String argv[]) {
    int p[] = new int[1001];
    int max = 0;
    for(int m = 2; m <= 32 ; m++) {
      for(int n = m-1; n >= 0 ; n = n-2) {
        if(coprime(n,m)) {
          int a = calcA(m,n), b= calcB(m,n), c = calcC(m,n);
          for(int k = 1; k*(a+b+c) <= 1000; ++k) {
            p[k*(a+b+c)]++;
            if(p[k*(a+b+c)] > p[max]) max = k*(a+b+c);
          }
        }
      }
    } 
    System.out.println(max + "->" + p[max]);
    System.out.println(120 + "->" + p[120]);
  }
}
