/*
Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down, there are exactly 6 routes to the bottom right corner.

How many such routes are there through a 20×20 grid?
*/
class Problem15 {
	private final static int size = 7;
	public static void main(String argv[]) {
		long matrix[][] = new long[size][size];
		for(int i = 0; i < size; ++i)
			matrix[i][0] = matrix[0][i] = i+2;
		for(int i = 1; i < size; ++i)
			for(int j = 1; j < size; ++j)
				matrix[i][j] = matrix[i-1][j] + matrix[i][j-1];
		System.out.println(matrix[size-1][size-1]);
	}
}
