/*
You are given the following information, but you may prefer to do some research for yourself.

- 1 Jan 1900 was a Monday.
- Thirty days has September,
  April, June and November.
  All the rest have thirty-one,
  Saving February alone,
  Which has twenty-eight, rain or shine.
  And on leap years, twenty-nine.
- A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.

How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
 */

class Problem19 {
	private static int calendars[][] = {
		{31,28,31,30,31,30,31,31,30,31,30,31}, // 0 for normal
		{31,29,31,30,31,30,31,31,30,31,30,31}  // 1 for leap
	};
	private static int firstDay = (0+365)%7; //information give: monday on Jan 1st 1900
	private static int firstYear = 1901;
	private static int lastYear = 2000;
	public static void main(String argv[]) {
		int sundays = 0;
		int firstDayOfMonth = firstDay;
		for(int currentYear = firstYear; currentYear <= lastYear; currentYear++) {
			int indexYear;
			// determine if leap year or not
			if(currentYear % 4 != 0)
				indexYear = 0;
			else if(currentYear % 100 == 0 && currentYear % 400 != 0)
				indexYear = 0;
			else
				indexYear = 1;
			// counting sundays at the beginning of the month
			for(int month = 0; month < calendars[indexYear].length; ++month) {
				if(firstDayOfMonth == 6)
					sundays++;
				firstDayOfMonth = (firstDayOfMonth + calendars[indexYear][month]) % 7;
			}
		}
		System.out.println(sundays);
	}
}
