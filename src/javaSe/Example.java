package javaSe;

public class Example {

	public static boolean isNum(String s) {
		// 把字符串拆成单个字符数组
		char[] cs = s.toCharArray();
		for (char c : cs) {
			// Character.isDigit(c) 判断是否是数字
			if (!(c >= '0' && c <= '9')) {
				// 判断单个字符是否在0-9之间
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
