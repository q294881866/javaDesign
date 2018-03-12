package oop;

/**
 * 继承测试
 * @author ppf@jiumao.org
 * @date 2017年2月14日
 */
public class ExtendTest extends Base{

	static ExtendTest child = new ExtendTest();
	public ExtendTest() {	}
	
	public static void main(String[] args) {
		ExtendTest.say();
		ExtendTest.Inner.say();
	}
}
