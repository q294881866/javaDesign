package javaSe.special.enums;
/**
 * 测试枚举的特性<br>
 * @author ppf@jiumao.org
 * @date 2017年1月17日
 */
public enum Color {
	RED("红色", 3), GREEN("绿色", 2), BLUE("蓝色", 1);
	// 成员变量
	private String name;
	private int index;
	// 构造方法
	Color(String name, int index) {
		this.name = name;
		this.index = index;
	}
	private Color() {
	}
	// 普通方法 与 普通java类没有区别
	public static String getName(int index) {
		for (Color c : Color.values()) {
			if (c.index == index) {
				return c.name;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		/*java.lang.Enum*/
		System.out.println(RED.ordinal());
		System.out.println(BLUE.ordinal());
		System.out.println(RED.name());
	}
}
