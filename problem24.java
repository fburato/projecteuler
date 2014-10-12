/*
A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the digits 1, 2, 3 and 4. If all of the permutations are listed numerically or alphabetically, we call it lexicographic order. The lexicographic permutations of 0, 1 and 2 are:

012   021   102   120   201   210

What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 */

class Problem24 {
	
	public static int factorial(int n) {
		int prod = 1;
		for(int i = 1; i <= n; ++i)
			prod *= i;
		return prod;
	}

	public static void shiftLeft(int v[],int  pos) {
		for(int i = pos; i < v.length -1; ++i)
			v[i] = v[i+1];
	}
	public static void main(String argv[]) {
		final int arrSize = 10;
		int position = 1000000; 
		int arr[] = new int[arrSize];
		for(int i = 0; i < arrSize; ++i)
			arr[i] = i;
		int res[] = new int[arrSize];
		int size = arrSize;
		for(int i = 0; i < arrSize; ++i) {
			int choices = factorial(size-1);
			int registerPos = 0;
			// find the position holding the correct number
			while(choices*registerPos < position)
				registerPos++;
			if(registerPos>0) registerPos--;
			position -= choices*registerPos;
			System.out.println(arr[registerPos]);
			res[i] = arr[registerPos];
			shiftLeft(arr,registerPos);
			size--;
		}
	}
}
