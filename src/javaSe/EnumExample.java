package javaSe;

import java.util.Date;

public class EnumExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		WeekDay1 weekDay = WeekDay1.MON;
//		System.out.println(weekDay.nextDay());
		
		WeekDay weekDay2 = WeekDay.FRI;
		
	}

	public enum WeekDay{

		SUN(1),MON(),TUE,WED,THI,FRI,SAT;
		private WeekDay() {

			System.out.println("default method");
		}

		private WeekDay(int day) {
			System.out.println("day==" + day);
		}

	}
	
}
