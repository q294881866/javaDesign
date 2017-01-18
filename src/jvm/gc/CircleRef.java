package jvm.gc;


/**
 * ���ü������㷨���ܽ�������⣺<br>
 * 1.ѭ������ -XX:+PrintGCDetails
 * @author ppf@jiumao.org
 * @date 2017��1��11��
 */
public class CircleRef {
	private static final int _1MB = 1024*1024;
	public Object instance = null;
	/**
	 * �����Ա���Ե�Ψһ�������ռ���ڴ棬�Ա���GC��־�п��Ƿ񱻻��չ�
	 */
	private byte[] bigSize = new byte[2*_1MB];
	
	public static void testGC() {
		CircleRef a	 = new CircleRef();
		CircleRef b	 = new CircleRef();
		a.instance=b;b.instance=a;
		
		a=null;b=null;
		
		System.gc();
	}
	
	public static void main(String[] args) {
		testGC();
	}
}
