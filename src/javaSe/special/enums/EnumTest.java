package javaSe.special.enums;

/**
 * ö��ʾ��<br>
 * @author ppf@jiumao.org
 * @date 2017��1��17��
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
		/**����Ӧ��ֵ*/
		private Integer value;
		Human() {System.out.println("default");}
		private Human(Integer value) {
			this.value = value;
			System.out.println("construct with Integer");
		}
	}
	
}
