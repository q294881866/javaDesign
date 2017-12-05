package jvm;
/**
 * VM Args:-Xss2m 虚拟机栈深度
 * @author ppf@jiumao.org
 * @date 2017年1月10日
 */
public class JavaVMStackOOm {
	
	private void dontStop() {
		while (true) {
		}
	}
	
	public void stackLeakByThread() {
		while (true) {
			Thread thread= new Thread(new Runnable() {
				@Override
				public void run() {
					dontStop();
				}
			});
			
			thread.start();
		}
	}

	
	public static void main(String[] args) {
		JavaVMStackOOm oom = new JavaVMStackOOm();
		oom.stackLeakByThread();
	}
}
