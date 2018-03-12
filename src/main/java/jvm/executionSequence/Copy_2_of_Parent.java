package jvm.executionSequence;

public class Copy_2_of_Parent {
	
	static {
		System.out.println("父类静态代码块");
	}
	{
		System.out.println("父类代码块");
	}

	public Copy_2_of_Parent() {
		System.out.println("父类构造方法");
	}

	static class Sub extends Copy_2_of_Parent {
		static {
			System.out.println("子类静态代码块");
		}
		{
			System.out.println("子类代码块");
		}

		public Sub() {
			System.out.println("子类构造方法");
		}
	}

	public static void main(String[] args) {
		Copy_2_of_Parent b = new Sub();
	}
}
