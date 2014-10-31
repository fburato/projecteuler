/*
In England the currency is made up of pound, £, and pence, p, and there are eight coins in general circulation:

1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
It is possible to make £2 in the following way:

1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
How many different ways can £2 be made using any number of coins?
 */

class Problem31 {
  private static int values[] = {  1,  2, 5,10,20,50,100,200};
  private static final int max = 200;
  private static int matrix[][] = new int[8][max];
  private static void init() {
    for(int i = 0; i < max; ++i)
      matrix[0][i] = 1;
    for(int i = 0; i < values.length; ++i)
      matrix[i][0] = 1;
  }
  
  private static void completeMatrix() {
    for(int i = 1; i < values.length; ++i) 
      for(int j = 1; j < max; ++j) {
        int counter = 0;
        for(int k = i; k >= 0; k--)
          if(values[k] == j+1)
            counter++;
          else if(values[k] < j+1) {
            int sum = j-values[k];
            counter = counter + matrix[k][sum];
          }
        matrix[i][j] = counter;
      } 
  }
  public static void main(String argv[]) {
    init();
    completeMatrix();
    System.out.println(matrix[7][199]);
  }
}
