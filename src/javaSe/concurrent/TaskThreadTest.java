package javaSe.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

import org.junit.Test;

public class TaskThreadTest {

	@Test// ��Σ�CyclicBarrier ��ע����߳�
	public void cyclicBarrierTest() throws Exception {
		ExecutorService service = Executors.newFixedThreadPool(3);
		final CyclicBarrier cb = new CyclicBarrier(3);
		Runnable runnable = () -> {
			try {
				doSomething(cb);
				cb.await();// �ȴ�������
				doSomething(cb);
				cb.await();// �ڶ��μ���
			} catch (Throwable a) {}
		};
		for (int i = 0; i < 3; i++) {
			service.execute(runnable);
		}
		service.shutdown();
	}
	 
	static int taskCount = 0;
	private static void doSomething(CyclicBarrier cb) throws InterruptedException {
		Thread.sleep((long) (Math.random() * 1000));
		System.out.println("�̣߳�" + Thread.currentThread().getName() + "����");
		if (2 == cb.getNumberWaiting()) {// Ŀǰ�Ѿ��ȴ�2���������ǵ��������������
			System.out.println("�������"+(++taskCount));
		}
	}
	
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
		System.out.println("I am single lady" + (null == o ? "" : o));
	}
}
// CountDownLatch һ�Σ���עһ���߳�
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
			TaskThreadTest.doWork(null);// do something
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
			TaskThreadTest.doWork(steps--);// do something
			done.countDown();
		} catch (Exception e) {
		}
	}

}
