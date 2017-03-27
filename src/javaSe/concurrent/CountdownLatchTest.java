package javaSe.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class CountdownLatchTest {

	@Test
	public void waitAnother() throws Exception {
		final int N = 3;
		CountDownLatch start = new CountDownLatch(1);
		CountDownLatch done = new CountDownLatch(N);
		for (int i = 0; i < N; ++i) 
			new Thread(new Worker(start, done)).start();
		start.countDown();// ��ʼ�ź�
		done.await();// �ȴ����
	}

	@Test
	public void workTogether() throws Exception {
		final int N = 3;
		CountDownLatch done = new CountDownLatch(N);
		for (int i = 0; i < N; i++) 
			new Thread(new WorkerSteps(done, N)).start();
		done.await();// �ȴ����
	}

	static void doWork(Object o) throws Exception {
		Thread.sleep(new Random().nextInt(10));
		System.out.println("I am single lady" + (null==o?"":o));
	}
}

class Worker implements Runnable {
	private final CountDownLatch start;
	private final CountDownLatch done;

	Worker(CountDownLatch start, CountDownLatch done) {
		super();
		this.start = start;
		this.done = done;
	}

	@Override
	public void run() {
		try {
			start.await(); // �����ȴ��ź�ִ��
			CountdownLatchTest.doWork(null);// do something
			done.countDown();// ������һ
		} catch (Exception e) {
		}
	}
}

class WorkerSteps implements Runnable {
	private final CountDownLatch done;
	private static int steps;

	WorkerSteps(CountDownLatch done, int steps) {
		super();
		this.done = done;
		WorkerSteps.steps = steps;
	}

	@Override
	public void run() {
		try {
			CountdownLatchTest.doWork(steps--);// do something
			done.countDown();
		} catch (Exception e) {
		}
	}

}
