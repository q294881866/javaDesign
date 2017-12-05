package javaSe.special.sugar;

import java.util.Collection;
import java.util.Objects;

/**
 * 语法糖
 * @author ppf@jiumao.org
 * @date 2017年1月25日
 */
public class AssertTest {
	public static void main(String[] args){
		try {
			String s = "null";
			assert ("null".equals(s));
			assert (s==null):"s isNonNull";
		} catch (Throwable e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
