package javaSe;

public class Example {

	public static boolean isNum(String s) {
		// ���ַ�����ɵ����ַ�����
		char[] cs = s.toCharArray();
		for (char c : cs) {
			// Character.isDigit(c) �ж��Ƿ�������
			if (!(c >= '0' && c <= '9')) {
				// �жϵ����ַ��Ƿ���0-9֮��
				return false;
			}
		}
		return true;
	}
	
	public static void increment() {
		int i=1;
        int j=0;
        j=(i++)*(++i);
        System.out.println(i);//3
        System.out.println(j);//3
	}
	public static void main(String[] args) {
		increment();
	}
}
