/*
The prime 41, can be written as the sum of six consecutive primes:

41 = 2 + 3 + 5 + 7 + 11 + 13
This is the longest sum of consecutive primes that adds to a prime below one-hundred.

The longest sum of consecutive primes below one-thousand that adds to a prime, contains 21 terms, and is equal to 953.

Which prime, below one-million, can be written as the sum of the most consecutive primes?
*/
import java.util.ArrayList;

class Problem50 {
  public static boolean isPrime(int n, ArrayList<Integer> primes) {
    if(n <= 1)
      return false;
    int limit = (int) Math.sqrt(n);
    boolean stillPrime = true;
    for(int i = 0; stillPrime && i < primes.size() && primes.get(i) <= limit; ++i)
      stillPrime = n % primes.get(i) != 0;
    return stillPrime;
  }
  
  
  public static int indexOfSmallest(int prime, ArrayList<Integer> subsequence) {
    int start = 0;
    int end = subsequence.size()- 1;
    int med = 0;
    while(start < end) {
      med = (start+end)/2;
      if(subsequence.get(med) <= prime)
        start = med +1;
      else
        end = med;
    }
    while(med < subsequence.size() && subsequence.get(med) <= prime)
      med++;
    return med -1;
  }
  
  
  public static void main(String argv[]) {
    final int limit = 1000000;
    ArrayList<Integer> primes = new ArrayList<>();
    ArrayList<Integer> subsequence = new ArrayList<>();
    primes.add(2);
    subsequence.add(0);
    int lastprime = 2;
    for(int i = 3; i < limit; ++i)
      if(isPrime(i,primes)) {
        subsequence.add(primes.get(primes.size()-1) + subsequence.get(subsequence.size()-1));
        primes.add(i);
      }
    int max = 1;
    for(int i = subsequence.size() -2 ; i >= 0; --i ){
      int prime = subsequence.get(i+1) - subsequence.get(i);
      int indexOfSmallest = indexOfSmallest(prime,subsequence);
      int start = 0;
      while(indexOfSmallest - start > max && indexOfSmallest >= start) {
        if(indexOfSmallest < subsequence.size() && subsequence.get(indexOfSmallest) - subsequence.get(start) == prime) {
          max = indexOfSmallest - start;
          System.out.println(prime + " " + (indexOfSmallest - start) + " " + start + " " + indexOfSmallest);  
        } else {
          indexOfSmallest++;
          while(start < subsequence.size() && indexOfSmallest < subsequence.size() && subsequence.get(indexOfSmallest) - subsequence.get(start) > prime) {
            start++;
          }
        }
      }
    }
  }
}
