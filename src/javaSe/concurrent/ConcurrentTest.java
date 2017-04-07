package javaSe.concurrent;

import static org.junit.Assert.*;

import java.util.ArrayList;
/**
 * �������API����
 */
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.junit.Test;

public class ConcurrentTest  {
	
	static int count = 0;
	final static Lock lock = new ReentrantLock();
	public static void runTest(Runnable target) throws Exception {
		for (int i = 0; i < 10000; i++) {
			new Thread(target).start();
		}
		Thread.sleep(20*1000);
		System.out.println("count: "+count);
	}
	@Test
	public void tryLockTest() throws Exception {
		Runnable target = 
				()->{if(lock.tryLock()){try{ ++count; }finally{ lock.unlock();}}};
		runTest(target);// tryLock <= 10000			
	}
	
	@Test
	public void lockTest() throws Exception {
		Runnable target = ()->{try{lock.lock(); ++count; }finally{ lock.unlock();}};
		runTest(target); // 10000
	}
	
	

	@Test
	public void copyOnWriteArrayListTest() throws Exception {
		Collection<String> c = new CopyOnWriteArrayList<String>(new String[] { "a", "b", "c" });
		Iterator<String> it = c.iterator();
		while (it.hasNext()) {
			String s = it.next();
			try {
				it.remove();
			} catch (UnsupportedOperationException e) {
				System.out.println("��������֧���쳣��ɾ����" + s);
				c.remove(s);
			}
		}
	}

	@Test
	public void semaphoreTest() throws Exception {
		ExecutorService service = Executors.newCachedThreadPool();
		final Semaphore sp = new Semaphore(3);
		for (int i = 0; i < 10; ++i) {
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						sp.acquire();
						Thread.sleep((long) (Math.random() * 1000));// do something
						System.out.println(Thread.currentThread().getName() + "����");
						sp.release();
						System.err.println("���ɽ�������̣߳�" + sp.availablePermits());
					} catch (Exception e) {
					}
				}
			};
			service.execute(runnable);
		}
	}

	@Test
	public void callableAndFutureTest() throws Exception {
		class OneCallable implements Callable<String>{
			@Override
			public String call() throws Exception {
				Thread.sleep(new Random().nextInt(1000));
				return "���ԣ�Callable�ӿ�";
			}
		}
		
		FutureTask<String> fu = new FutureTask<>(new OneCallable());
		new Thread(fu).start();
		System.out.println(fu.get(1, TimeUnit.SECONDS));
		
		ExecutorService service = Executors.newCachedThreadPool();
		List<Future<String>> futures = new ArrayList<>(10);
		for (int i = 0; i < 10; i++) {
			futures.add(service.submit(new OneCallable()));
		}
		for (Future<String> f : futures) {
			while (!f.isDone()) {
				Thread.sleep(500);
			} 
			System.out.println(""+f.get());
		}
	}
	
	
	@Test
	public void conditionTest() throws Exception {
		BoundedBuffer buffer = new BoundedBuffer();
		Runnable w = () -> {
			for (int i = 0; i < 200; i++) {
				try {
					buffer.put("adc");
				} catch (InterruptedException e) {}
			}
		};
		Runnable r = () -> {
			for (int i = 0; i < 200; i++) {
				try {
					buffer.take();
				}catch (InterruptedException e) {}
			}
		};
		new Thread(r).start();new Thread(w).start();
	}
	
	
	@Test
	public void rwLockTest() throws Exception {
		// ���� 1
		System.out.println(Util.getCache());
		// ���� 2
		final RWTreeMap data = new RWTreeMap();
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < 100000; i++) {
			final int count = i;
			if (0 == count % 3) {
				service.execute(new Runnable() {
					@Override
					public void run() {
						for (int j = 0; j < 3; j++) {
							data.put("key" + (count+j), "value" + (count+j));
						}
					}
				});
			}else { 
				service.execute(new Runnable() {
					@Override
					public void run() { // ������ key1-3 
						System.out.println(data.get("key" + count % 4));
					}
				});
			}
		}
		service.shutdown();// �ȴ�������ɣ��ر��̳߳�
	}

	
	@Test
	public void timerTest() throws Exception {
		long initialDelay = 0, period = 1;// ��λΪ��
		ScheduledExecutorService scheduled = new ScheduledThreadPoolExecutor(1);
		Runnable r = () -> {System.out.println("do something");};
		scheduled.scheduleAtFixedRate(r, initialDelay, period, TimeUnit.SECONDS);

		class Task extends TimerTask {
			@Override
			public void run() {System.out.println("��ʱ����");}
		}
		new Timer().schedule(new Task(), initialDelay * 1000, period * 1000);
		Util.sleep();
	}

	
}

class Util{
	/**
	 * ���1s�������ߣ����������ȴ��������
	 */
	public static void sleep() {
		while (true) {
			try {
				Thread.sleep(1*1000);
			} catch (Exception e) {}
		}
	}
	
	public static String getCache() {
		String cache = null;
		ReadWriteLock rwLock = new ReentrantReadWriteLock();
		rwLock.readLock().lock();// �Ӷ���
		if (null == cache) {
			rwLock.readLock().unlock();
			rwLock.writeLock().lock();// ��д��
			cache = "data";
			rwLock.writeLock().unlock();// unlockд��
			rwLock.readLock().lock();
		}
		rwLock.readLock().unlock();// unlock����
		return cache;
	}
}


class RWTreeMap {
	private final Map<String, String> data = new TreeMap<String, String>();
	private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	private final Lock r = rwLock.readLock();// д��
	private final Lock w = rwLock.writeLock();// ����
	
	public String get(String key) {
		r.lock();
		try {
			return data.get(key);
		} finally { r.unlock(); }
	}
	
	public String put(String key, String value) {
		w.lock();
		try {
			return data.put(key, value);
		} finally { w.unlock(); }
	}
}


class BoundedBuffer { // �н���� ��������jdk ԭ��ͬ ArrayBlockingQueue
	final Lock lock = new ReentrantLock();
	final Condition notFull = lock.newCondition();
	final Condition notEmpty = lock.newCondition();

	final Object[] items = new Object[100];
	int putIndex, takeIndex, count;

	public void put(Object x) throws InterruptedException {
		lock.lock();
		try {
			while (count == items.length) notFull.await();
			items[putIndex] = x;
			if (++putIndex == items.length) putIndex = 0;
			++count; // Ԫ������1
			notEmpty.signal();// ֪ͨȡ�߳�
		} finally {
			lock.unlock();
		}
	}

	public Object take() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0) notEmpty.await();
			Object x = items[takeIndex];
			if (++takeIndex == items.length) takeIndex = 0;
			--count; // ȡһ������0ѭ��ȡ
			notFull.signal(); // ֪ͨ����߳�
			return x;
		} finally {
			lock.unlock();
		}
	}
}
