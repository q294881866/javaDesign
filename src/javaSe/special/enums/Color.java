package javaSe.special.enums;
/**
 * ����ö�ٵ�����<br>
 * @author ppf@jiumao.org
 * @date 2017��1��17��
 */
public enum Color {
	RED("��ɫ", 3), GREEN("��ɫ", 2), BLUE("��ɫ", 1);
	// ��Ա����
	private String name;
	private int index;
	// ���췽��
	Color(String name, int index) {
		this.name = name;
		this.index = index;
	}
	private Color() {
	}
	// ��ͨ���� �� ��ͨjava��û������
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
