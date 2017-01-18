package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10m
 * 
 * @author ppf@jiumao.org
 * @date 2017��1��11��
 */
public class RuntimeConstantPoolOOM {
	
	public static void inJDK6() {
		//����Full GC���ճ�������Ϊ�����԰��ַ����ŵ�������
		List<String> list = new ArrayList<String>();
		//10m��PermSize��Integer��Χ���㹻����OOM��
		int i=0;
		while (true) {
			list.add(String.valueOf(i++).intern());
		}
	}
	
	/**
	 * in jdk1.6 false false
	 * in jdk1.7 1.8 true false
	 */
	public static void internTest() {
		String s1 = new StringBuilder("�����").append("���").toString();
		System.out.println(s1==s1.intern());
		
		String s2 = new StringBuilder("ja").append("va").toString();
		System.out.println(s2==s2.intern());
	}

	public static void main(String[] args) {
		internTest();
	}
}
