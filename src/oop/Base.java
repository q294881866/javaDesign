package oop;
/**
 * ���Լ̳еĸ���
 * @author ppf@jiumao.org
 * @date 2017��2��14��
 */
public class Base {

	public static class Inner{
		public static void say() {
			System.out.println("�ڲ���");
		}
	}
	
	public static void say() {
		System.out.println("��ͨ����");
	}
}

class NotPublicClazz{
	
	public void say() {
		System.out.println("�ⲿ��");
	}
}