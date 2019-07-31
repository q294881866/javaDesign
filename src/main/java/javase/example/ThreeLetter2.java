package javase.example;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
/**
 * 问题描述：
 * 打印 'A', 'B', 'C', 'D', 'E', 'F' 
 * 三个字母任意组合，顺序不限，如AAB=BAA
 * 方法一：利用素数没有公用数的性质
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
	 * 打印字母 一个字母对一个质数， 
	 * ABC等于CBA,对应质数乘积
	 * 由于质数特性不会出现重复
	 * 
	 */
	public static void printLetter() {
		Map hm = new HashMap();
		int[] array = new int[3];
		int min, middle, max;
		for (int i = 0; i < letter.length; i++) {
			for (int j = 0; j < letter.length; j++) {

				for (int j2 = 0; j2 < letter.length; j2++) {

					
					// 字母输出样式
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
	 * 遍历hashmap
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
