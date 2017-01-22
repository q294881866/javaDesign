package jvm.executionSequence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MethodofStatic {
	AtomicInteger obj = new AtomicInteger();
	static AtomicInteger sta = new AtomicInteger();
	static int i = 0;int j=0;
	public static void testSta() throws InterruptedException{
		i++;	sta.incrementAndGet();
	}

	public void testObj() throws InterruptedException{
		j++;	obj.incrementAndGet();
	}

	static final ExecutorService service = Executors.newFixedThreadPool(8);

	public static void main(String[] args) throws Exception {
		MethodofStatic ms = new MethodofStatic();
		Runnable r1 = new Test(ms);
		Runnable r2 = new Test(null);

		for (int i = 0; i < 8; i++) {
			service.execute(0 == i % 2 ? r1 : r2);
		}
		while (Test.threadCount != 0) {
			Thread.sleep(1 * 1000);
		}
		System.out.println("对象方法执行次数：" + ms.obj+ ms.j);
		System.out.println("静态方法执行次数：" + ms.sta+ ms.i);// 静态要用类调用，不规范
	}
}

class Test implements Runnable {

	private MethodofStatic ms;
	static int threadCount = 0;

	public Test(MethodofStatic ms) {
		this.ms = ms;
	}

	@Override
	public void run() {
		threadCount++;
		for (int i = 0; i < 100; i++) {
			try {
				if (null == ms)	MethodofStatic.testSta();
				else			ms.testObj(); 
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		threadCount--;
	}

}
