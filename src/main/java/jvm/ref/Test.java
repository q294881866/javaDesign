package jvm.ref;
/**
 * 引用相关的例子
 * @author ppf@jiumao.org
 * @date 2017年1月18日
 */
public class Test {
	
	public static class Super{
		static{	System.out.println("super init");}
		public static int value = 123;
		public final static String NAME = "NAME";
	}
	
	public static class Sub extends Super{
		static{ System.out.println("sub init");}
	}
	
	public static void main(String[] args) {
		Super[] ss = new Super[10];
		System.out.println(Super.NAME);
	}

}
