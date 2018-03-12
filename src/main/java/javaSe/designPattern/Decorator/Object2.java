package javaSe.designPattern.Decorator;

public class Object2 implements Decorator{
	
	Source source = new Source();

	@Override
	public void m1() {
		System.out.println("before");
		source.m1();
		System.out.println("after ");
	}


	public static void main(String[] args) {
		new Object2().m1();
		
		
//		new Source().m1();
	}
}
