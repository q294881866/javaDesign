package oop;
/**
 * 测试继承的父类
 * @author ppf@jiumao.org
 * @date 2017年2月14日
 */
public class Base {

	public static class Inner{
		public static void say() {
			System.out.println("内部类");
		}
	}
	
	public static void say() {
		System.out.println("普通方法");
	}
}

class NotPublicClazz{
	
	public void say() {
		System.out.println("外部类");
	}
}