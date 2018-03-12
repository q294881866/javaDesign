package jvm.ref;

public class Test4Interface {

	public interface ISuper{
		void m1();
		String ISUPER = "ISuper";
	}
	
	public interface ISub extends ISuper{
		void m2();
		String ISUB = "ISub";
	}
}
