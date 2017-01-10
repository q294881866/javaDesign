package executionSequence;

public class CopyOfParent {
	
	private String base = "base";
	static {
		System.out.println("���ྲ̬�����");
	}
	{
		System.out.println("��������");
	}

	public CopyOfParent() {
		System.out.println("����Base�Ĺ��췽���������ˣ�");
		System.out.println("����Base�ĳ�Ա����baseName����ʼ��Ϊ" + base);
		callName();
	}

	public void callName() {
		System.out.println("����Base��callName()�����������ˣ�");
		System.out.println(base);
	}

	static class Sub extends CopyOfParent {
		static final String SUB_BASE="SUB_BASE"; 
		private String subString = "sub";
		static {
			System.out.println("���ྲ̬�����");
		}
		{
			System.out.println("��������");
		}

		public Sub() {
			System.out.println("���๹�췽��");
		}


		public void callName() {
			System.out.println("����Sub��callName()����");
			System.out.println(subString);
			System.out.println(SUB_BASE);
		}
	}

	public static void main(String[] args) {
		CopyOfParent b = new Sub();
	}
}
