package jvm.executionSequence;

public class Test4InnerClass {

	
	public static void main(String[] args) {
		//��̬���ڲ���
		new Parent.Sub().call();
		//�Ǿ�̬���ڲ���
//		new Parent().new Sub().call();
	}
}
