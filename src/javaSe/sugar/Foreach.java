package javaSe.sugar;

import java.util.Collection;

/**
 * �﷨��
 * @author ppf@jiumao.org
 * @date 2017��1��25��
 */
public class Foreach {
	
	public static void forArray(String... strs) {
		for (String s : strs) { }
	}
	
	public static void forCollection(Collection<String> cols ) {
		for (String s : cols) { }
	}

}
