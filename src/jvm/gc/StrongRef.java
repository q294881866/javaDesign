package jvm.gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.WeakHashMap;

/**
 * ǿ����ʾ��<br>
 * 
 * @author ppf@jiumao.org
 * @date 2017��1��19��
 */
public class StrongRef {

	public static void main(String[] args) {
		StrongRef sRef = new StrongRef();
		sRef = null;// ��ûʲô����
		// sRef ... do something
	}

	public static void SoftRef() {
		String s1 = "abc";
		// ������
		SoftReference<String> softRef = new SoftReference<String>(s1);
		// ������
		WeakReference<String> weakRef = new WeakReference<String>(s1);
		// ������
		PhantomReference<String> noneRef = new PhantomReference<String>(s1,null);
		
	}
}
