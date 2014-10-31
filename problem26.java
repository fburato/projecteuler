/*
A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2 to 10 are given:

1/2	= 	0.5
1/3	= 	0.(3)
1/4	= 	0.25
1/5	= 	0.2
1/6	= 	0.1(6)
1/7	= 	0.(142857)
1/8	= 	0.125
1/9	= 	0.(1)
1/10	= 	0.1
Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring cycle.

Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.
*/
import java.util.LinkedList;
import java.util.Iterator;

class Problem26 {
  public static int cycleLength(int d) {
    int start = 10, period = 0;
    LinkedList<Integer> afterPoint = new LinkedList<>();
    boolean stop = false;
    while(! stop) 
      if(start == 0)
        stop = true;
      else if(start < d){
        afterPoint.add(start);
        start *= 10;  
      } else {
        // start >= d
        int quot = start / d, counted = 0;
        // look for quot in afterPoint counting the period
        boolean found = false;
        for(Iterator<Integer> i = afterPoint.iterator(); ! found && i.hasNext(); counted++) {
          int num = i.next();
          found = num == start;
        }
        stop = found;
        if(stop)
          period = afterPoint.size() - counted + 1;
        else 
          afterPoint.add(start);
        // updating start to the reminder
        start = (start - quot *d)*10;
      }
    return period;
  }
  
  public static void main(String argv[]) {
    int max = -1, period;
    for(int i = 2; i < 1000; i++)
      if(cycleLength(i) > max)
        max = i;
    System.out.println(max);
  }
}
