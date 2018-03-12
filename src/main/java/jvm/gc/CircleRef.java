package jvm.gc;


/**
 * 引用计数器算法不能解决的问题：<br>
 * 1.循环引用 -XX:+PrintGCDetails
 */
public class CircleRef {
	private static final int _1MB = 1024*1024;
	public Object instance = null;
	/**
	 * 这个成员属性的唯一意义就是占点内存，以便在GC日志中看是否被回收过
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
