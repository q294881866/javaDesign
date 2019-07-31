package javase.special.sugar;


public class AutoBoxing {

	public static void main(String[] args) {
//		t1();
		
		t2();
	}


	public static void t2() {
		Integer i1 = 1;
		Integer i2 = 2;
		Integer i3 = 3;
		Long l = 3L;
		
		System.out.println((i3==(i2+i1))+" | "+(l==(i2+i1)));
	}


	public static void t1() {
		Integer i1 = 127;
		Integer i2 = 127;
		Integer i3 = 128;
		Integer i4 = 128;
		System.out.println((i1==i2)+" | "+(i3==i4));
		
		Object o = null;//requireNonNull
		int i = (Integer) o;//不是0
	}
	
	
}
