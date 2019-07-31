package javase.basic;

public class ExceptionTest {

	public static void main(String[] args) throws Exception {

		ExceptionTest.t1("1");
	}

	public static int t1(String t) throws ArithmeticException {
		Integer i;
		try {
			i = Integer.parseInt(t);
			return i / i - 1;
		} catch (NullPointerException e) {
			return 0;
		} catch (ArithmeticException e) {
			throw e;
		} finally {
			i = 2;
		}
	}
}
