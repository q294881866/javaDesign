package javaSe.basic.dataStructure.base;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * ��׺���ʽ�Ľ������磺AB+C*
 * �Զ�����Ϊһ�����ķָ� �ֺŽ��� 4,5,+,3,*,;
 */
public class SuffixExpression {
	public static int explain(String suffixExpression){
		int tmp = 0;
		Stack stack = new Stack();
		//��������ĺ�׺���ʽ������,�ָ�ÿ���ֶ� �ֺ�;����
		String[] s = suffixExpression.split(",");
		for (int i = 0; i < s.length; i++) {
			if (isNumeric(s[i])) {//1.��������֣�һֱ��ջ
				stack.add(s[i]);
			}else if(isComputer(s[i])) {//2.����Ǽ������ִ�����㣬��ջ������
				int x = Integer.parseInt((String) stack.remove());
				int y = Integer.parseInt((String) stack.remove());
				tmp = computer(x, y, s[i]);
				stack.add(tmp+"");
				
			}else if (";".equals(s[i])) {//3.����;�ֺŽ�����
				return Integer.parseInt((String) stack.remove());
			}
		}
		return tmp;
	}
	
	/**
	 * �Ӽ��˳�����
	 */
	private static int computer(int x,int y,String key){
		switch (key) {//switch �����ж�
		case "*":
			return x*y;

		case "+":
			return x+y;
			
		case "/":
			return x/y;
			
		case "-":
			return y-x;
		}
		return 0;
	}
	
	/**
	 * �ж��Ƿ�������
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	/**
	 * �ж��ǲ���+-/\*
	 */
	 public static boolean isComputer(String str) {
			Pattern pattern = Pattern.compile("[\\+\\-\\*/]");
			Matcher isNum = pattern.matcher(str);
			if (!isNum.matches()) {
				return false;
			}
			return true;
	}
	
	public static void main(String[] args) {
		int i = explain("4,5,+,3,*");
		System.out.println(i);
	}
}
