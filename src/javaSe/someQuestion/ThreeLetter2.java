package someQuestion;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
/**
 * ����������
 * ��ӡ 'A', 'B', 'C', 'D', 'E', 'F' 
 * ������ĸ������ϣ�˳���ޣ���AAB=BAA
 * ����һ����������û�й�����������
 * @author Administrator
 *
 */
public class ThreeLetter2 {
	
	
	public static void main(String[] args) {
		printLetter();
	}
	
	static char[] letter = { 'A', 'B', 'C', 'D', 'E', 'F' };
	static char[] prime = {2 ,3 ,5 ,7 ,11 ,13 };

	/**
	 * ��ӡ��ĸ һ����ĸ��һ�������� 
	 * ABC����CBA,��Ӧ�����˻�
	 * �����������Բ�������ظ�
	 * 
	 */
	public static void printLetter() {
		Map hm = new HashMap();
		int[] array = new int[3];
		int min, middle, max;
		for (int i = 0; i < letter.length; i++) {
			for (int j = 0; j < letter.length; j++) {

				for (int j2 = 0; j2 < letter.length; j2++) {

					
					// ��ĸ�����ʽ
					String s = "" + letter[i] + letter[j] + letter[j2];
					int key = prime[i]*prime[j]*prime[j2];
					hm.put(key, s);

				}
			}
		}

		// print hm
		iteratorHashMap(hm);

	}

	/**
	 * ����hashmap
	 * 
	 * @param hm
	 */
	private static void iteratorHashMap(Map hm) {
		Iterator iter = hm.entrySet().iterator();

		while (iter.hasNext()) {
			Map.Entry entry = (Entry) iter.next();
			Object key = entry.getKey();
			// System.out.println(key+"key");
			Object val = entry.getValue();
			System.out.println(val);

		}
	}
}
