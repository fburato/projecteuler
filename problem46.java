/*
It was proposed by Christian Goldbach that every odd composite number can be written as the sum of a prime and twice a square.

9 = 7 + 2×1^2
15 = 7 + 2×2^2
21 = 3 + 2×3^2
25 = 7 + 2×3^2
27 = 19 + 2×2^2
33 = 31 + 2×1^2

It turns out that the conjecture was false.

What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?
*/
import java.util.ArrayList;
class Problem46 {
  public static boolean isPrime(int n) {
    if(n <= 1)
      return false;
    int limit = (int) Math.sqrt(n);
    boolean stillPrime = true;
    for(int i = 2; stillPrime && i <= limit; ++i)
      stillPrime = n % i != 0;
    return stillPrime;
  }
  public static boolean exists(ArrayList<Integer> v, int i) {
    int start = 0;
    int end = v.size();
    int med;
    boolean found = false;
    while(!found && start < end) {
      med = (start+end)/2;
      if(v.get(med) == i)
        found = true;
      else if(v.get(med) < i)
        start = med + 1;
      else
        end = med;
    }
    return found;
  }
  
  public static boolean isPerfectSquare(int n) {
    int sqrt = (int) Math.sqrt(n);
    return sqrt*sqrt == n;
  }
    
  public static void main(String argv[]) {
    int counterExample = -1;
    ArrayList<Integer> primesFound = new ArrayList<Integer>();
    for(int i = 3; counterExample == -1 &&  i > 0; i = i+2) {
      if(isPrime(i))
        primesFound.add(i);
      else {
        boolean found = false;
        int j;
        for(j = 0 ; !found && j < primesFound.size() ; ++j) {
          found = isPerfectSquare((i - primesFound.get(j))/2);
        }
        j--;
        if(!found && j >= 0) {
          counterExample = i; 
          System.out.println(i);
        } else {
          System.out.println(i + " = " + primesFound.get(j) + " + 2 * " + Math.sqrt((i - primesFound.get(j))/2));
        }
      }
    } 
  }
} 
