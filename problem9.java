/*
A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,

a^2 + b^2 = c^2
For example, 32 + 42 = 9 + 16 = 25 = 52.

There exists exactly one Pythagorean triplet for which a + b + c = 1000.
Find the product abc.

Formula: given m, n with m>n the triplet (2*m*n, m^2-n^2, m^2+n^2) is  pythagorean
*/
class Problem9 {
	public static long calcA(long m, long n) {
		return 2*m*n;
	}
	public static long calcB(long m, long n) {
		return m*m - n*n;
	}
	public static long calcC(long m, long n) {
		return m*m + n*n;
	}
	public static void main(String argv[]) {
		long a, b, c;
		for(long m = 2; m < 251 ; m++)
			for(long n = 1 ; n < m ; n++) {
				a = calcA(m,n); b = calcB(m,n); c = calcC(m,n);
				if(a+b+c==1000){
					System.out.println("(a,b,c)=(" + a + "," + b + "," + c + ")\na*b*c=" + a*b*c);
					return ;
				}
			}
		System.out.println("Couldn't find them :(");
	}
}
