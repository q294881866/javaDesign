package jvm.gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.WeakHashMap;

/**
 * 强引用示例<br>
 * 
 * @author ppf@jiumao.org
 * @date 2017年1月19日
 */
public class StrongRef {

	public static void main(String[] args) {
		StrongRef sRef = new StrongRef();
		sRef = null;// 这没什么意义
		// sRef ... do something
	}

	public static void SoftRef() {
		String s1 = "abc";
		// 软引用
		SoftReference<String> softRef = new SoftReference<String>(s1);
		// 弱引用
		WeakReference<String> weakRef = new WeakReference<String>(s1);
		// 虚引用
		PhantomReference<String> noneRef = new PhantomReference<String>(s1,null);
		
	}
}
