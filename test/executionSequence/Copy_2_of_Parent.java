package executionSequence;

public class Copy_2_of_Parent {
	
	static {
		System.out.println("���ྲ̬�����");
	}
	{
		System.out.println("��������");
	}

	public Copy_2_of_Parent() {
		System.out.println("���๹�췽��");
	}

	static class Sub extends Copy_2_of_Parent {
		static {
			System.out.println("���ྲ̬�����");
		}
		{
			System.out.println("��������");
		}

		public Sub() {
			System.out.println("���๹�췽��");
		}
	}

	public static void main(String[] args) {
		Copy_2_of_Parent b = new Sub();
	}
}
