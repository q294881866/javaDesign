package oop;

/**
 * �̳в���
 * @author ppf@jiumao.org
 * @date 2017��2��14��
 */
public class ExtendTest extends Base{

	static ExtendTest child = new ExtendTest();
	public ExtendTest() {	}
	
	public static void main(String[] args) {
		ExtendTest.say();
		ExtendTest.Inner.say();
	}
}
