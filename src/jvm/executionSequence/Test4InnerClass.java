package jvm.executionSequence;

public class Test4InnerClass {

	
	public static void main(String[] args) {
		//静态的内部类
		new Parent.Sub().call();
		//非静态的内部类
//		new Parent().new Sub().call();
	}
}
