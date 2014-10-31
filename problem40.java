/*
An irrational decimal fraction is created by concatenating the positive integers:

0.123456789101112131415161718192021...

It can be seen that the 12th digit of the fractional part is 1.

If dn represents the nth digit of the fractional part, find the value of the following expression.

d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000
*/
class Problem40{
  public static void main(String argv[]) {
    int d[] = {1,10,100,1000,10000,100000,1000000};
    int count = 0;
    int next = 0;
    int prod = 1;
    int num = 1;
    while(next < d.length) {
      String snum = "" + num;
      for(int i = 0;next < d.length && i < snum.length(); ++i) {
        count++;
        if(next < d.length && count == d[next]) {
          prod = prod * (snum.charAt(i) - '0');
          next++;
        }
      }
      num++;
    }
    System.out.println(prod);
  }
}
