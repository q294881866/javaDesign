package javaSe.sugar;

import java.util.Collection;
import java.util.Objects;

/**
 * �﷨��
 * @author ppf@jiumao.org
 * @date 2017��1��25��
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
