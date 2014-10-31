/*
The number 3797 has an interesting property. Being prime itself, it is possible to continuously remove digits from left to right, and remain prime at each stage: 3797, 797, 97, and 7. Similarly we can work from right to left: 3797, 379, 37, and 3.

Find the sum of the only eleven primes that are both truncatable from left to right and right to left.

NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
*/
class Problem37 {
  public static boolean isPrime(int n) {
    if(n <=1)
      return false;
    int limit = (int) Math.sqrt(n);
    boolean stillPrime = true;
    for(int i = 2; stillPrime && i <= limit; ++i)
      stillPrime = n % i != 0;
    return stillPrime;
  }
  
  public static boolean isTruncatablePrime(int n) {
    if(!isPrime(n))
      return false;
    boolean stillTruncatable = true;
    int power = 1;
    int ncopy = n/10;
    int nconstruct = n%10;
    while(stillTruncatable && ncopy > 0) {
      stillTruncatable = isPrime(ncopy) && isPrime(nconstruct);
      power = power * 10;
      nconstruct = nconstruct + (ncopy % 10)*power; 
      ncopy = ncopy / 10;
    }
    return stillTruncatable;
  }
  
  public static void main(String argv[]) {
    final int limit = 11;
    int count = 0;
    int sum = 0;
    for(int i = 10; i > 0 && count != limit; ++i){
      if(isTruncatablePrime(i)){
        System.out.println(i);
        count++;
        sum += i;
      }
    }
    
    if(count != 11){
      System.out.println("Not enough bits");
    } else {
      System.out.println(sum);
    }
  }
}
