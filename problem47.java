/*
The first two consecutive numbers to have two distinct prime factors are:

14 = 2 × 7
15 = 3 × 5

The first three consecutive numbers to have three distinct prime factors are:

644 = 2^2 × 7 × 23
645 = 3 × 5 × 43
646 = 2 × 17 × 19.

Find the first four consecutive integers to have four distinct prime factors. What is the first of these numbers?
*/
import java.util.ArrayList;

class Problem47{
  public static boolean isPrime(int n) {
    if(n <= 1)
      return false;
    int limit = (int) Math.sqrt(n);
    boolean stillPrime = true;
    for(int i = 2; stillPrime && i <= limit; ++i)
      stillPrime = n % i != 0;
    return stillPrime;
  }
  
  public static boolean contains(ArrayList<Integer> v, int n) {
    int start = 0;
    int end = v.size();
    int med;
    boolean found = false;
    while(!found && start < end) {
      med = (start+end)/2;
      if(v.get(med) == n)
        found = true;
      else if(v.get(med) < n)
        start = med+1;
      else 
        end = med;
    }
    return found;
  }
  
  public static int countPrimeDivisors(int n, ArrayList<Integer> primes) {
    int counter = 0;
    for(int p : primes) {
      if(n % p == 0) counter++;
    }
    return counter;
  }
  
  public static void main(String argv[]) {
    boolean stop = false;
    int firstWithFour = -1;
    int current = 2;
    ArrayList<Integer> primes = new ArrayList<>();
    for(; current > 0 && !stop;++current) {
      if(isPrime(current)) {
        primes.add(current);
        firstWithFour = -1;
      } else {
        int primeDivisors = countPrimeDivisors(current,primes);
        if(primeDivisors == 4) {
          if(firstWithFour == -1) {
            firstWithFour = current;
          } else {
            stop = current - firstWithFour +1 == 4;
          }
        } else {
          firstWithFour = -1;
        }
      }
    }
    System.out.println(firstWithFour);
  }
}
