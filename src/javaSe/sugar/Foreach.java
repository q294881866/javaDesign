package javaSe.sugar;

import java.util.Collection;

/**
 * 语法糖
 * @author ppf@jiumao.org
 * @date 2017年1月25日
 */
public class Foreach {
	
	public static void forArray(String... strs) {
		for (String s : strs) { }
	}
	
	public static void forCollection(Collection<String> cols ) {
		for (String s : cols) { }
	}

}
