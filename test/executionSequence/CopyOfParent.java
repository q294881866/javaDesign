package executionSequence;

public class CopyOfParent {
	
	private String base = "base";
	static {
		System.out.println("父类静态代码块");
	}
	{
		System.out.println("父类代码块");
	}

	public CopyOfParent() {
		System.out.println("父类Base的构造方法被调用了！");
		System.out.println("父类Base的成员变量baseName被初始化为" + base);
		callName();
	}

	public void callName() {
		System.out.println("父类Base的callName()方法被调用了！");
		System.out.println(base);
	}

	static class Sub extends CopyOfParent {
		static final String SUB_BASE="SUB_BASE"; 
		private String subString = "sub";
		static {
			System.out.println("子类静态代码块");
		}
		{
			System.out.println("子类代码块");
		}

		public Sub() {
			System.out.println("子类构造方法");
		}


		public void callName() {
			System.out.println("子类Sub的callName()方法");
			System.out.println(subString);
			System.out.println(SUB_BASE);
		}
	}

	public static void main(String[] args) {
		CopyOfParent b = new Sub();
	}
}
