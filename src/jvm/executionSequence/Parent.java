package jvm.executionSequence;

public class Parent implements ICallable{
	private String base = "base";
	@Override
	public void call() {
		System.out.print(ICallable.IBASE+" ");
		System.out.println(base);
	}

	public Parent() {
		call();
	}

	public static class Sub extends Parent {
		private String sub = "sub";

		@Override
		public void call() {
			System.out.print(ICallable.IBASE+" ");
			System.out.println(sub);
		}
	}

	public static void main(String[] args) {
		Parent b = new Sub();
	}

}

