package oop;

/**
 * ¼Ì³Ğ²âÊÔ
 * @author ppf@jiumao.org
 * @date 2017Äê2ÔÂ14ÈÕ
 */
public class ExtendTest extends Base{

	static ExtendTest child = new ExtendTest();
	public ExtendTest() {	}
	
	public static void main(String[] args) {
		ExtendTest.say();
		ExtendTest.Inner.say();
	}
}
