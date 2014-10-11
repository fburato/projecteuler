/*
A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.

Find the largest palindrome made from the product of two 3-digit numbers.
 */
class Problem4 {
	private static final int max = 999*999;
	private static final int min = 100*100;
	public static boolean isPalindrome(int n){
		if(n < 0)
			return false;
		// the number is positive, convert to string and check
		String nstring = "" + n;
		boolean isStillPalindrome = true;
		for(int i = 0; isStillPalindrome && i < nstring.length()/2; ++i)
			isStillPalindrome = nstring.charAt(i) == nstring.charAt(nstring.length()-1-i);
		return isStillPalindrome;
	}

	public static boolean isProductOf3Digit(int n) {
		if(n < min || n > max)
			return false;
		for(int i = 100; i < 999; i++)
			if(n % i == 0 && n/i >= 100 && n/i <=999)
				return true;
		return false;
	}
	
	public static void main(String argv[]) {
		for(int i = max; i >= min ; i--)
			if(isPalindrome(i) && isProductOf3Digit(i)){
				System.out.println(i);
				return ;
			}
	}
}
