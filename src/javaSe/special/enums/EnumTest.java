package javaSe.special.enums;

/**
 * 枚举示例<br>
 * @author ppf@jiumao.org
 * @date 2017年1月17日
 */
public class EnumTest {
	public static void main(String[] args) {
		System.out.println("---------enum---------");
		Human COME_IN = Human.MAN;
		//Cannot instantiate the type EnumTest.Human
//		Human ET = new Human(2);
	}

	public enum Human {
		MAN(1),
		WOMEN(0),
		UNKNOWN();
		/**所对应的值*/
		private Integer value;
		Human() {System.out.println("default");}
		private Human(Integer value) {
			this.value = value;
			System.out.println("construct with Integer");
		}
	}
	
}
