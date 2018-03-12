package jvm.gc;

/**
 * FinalizeGC
 */
public class FinalizeGC {

	public static FinalizeGC SAVE_HOOK = null;

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalize ");
		SAVE_HOOK = this;
	}
	
	public static void main(String[] args) throws Exception {
		SAVE_HOOK = new FinalizeGC();
		isAlive();
		isAlive();
	}

	public static void isAlive() throws Exception {
		SAVE_HOOK = null;
		System.gc();
		Thread.sleep(100);
		if (null!=SAVE_HOOK) {
			System.out.println("Im still alive :) ");
		} else {
			System.out.println("I am dead :( ");
		}
	}

}
