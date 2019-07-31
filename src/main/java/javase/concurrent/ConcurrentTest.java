package javase.concurrent;

import java.util.ArrayList;
/**
 * 并发类库API测试
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
                System.out.println("迭代器不支持异常，删除：" + s);
                c.remove(s);
            }
        }
    }


    @Test
    public void callableAndFutureTest() throws Exception {
        class OneCallable implements Callable<String>{
            @Override
            public String call() throws Exception {
                Thread.sleep(new Random().nextInt(1000));
                return "测试：Callable接口";
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
        // 测试 1
        System.out.println(Util.getCache());
        // 测试 2
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
                    public void run() { // 无意义 key1-3 
                        System.out.println(data.get("key" + count % 4));
                    }
                });
            }
        }
        service.shutdown();// 等待任务完成，关闭线程池
    }

    
    @Test
    public void timerTest() throws Exception {
        long initialDelay = 0, period = 1;// 单位为秒
        ScheduledExecutorService scheduled = new ScheduledThreadPoolExecutor(1);
        Runnable r = () -> {System.out.println("do something");};
        scheduled.scheduleAtFixedRate(r, initialDelay, period, TimeUnit.SECONDS);

        class Task extends TimerTask {
            @Override
            public void run() {System.out.println("定时任务");}
        }
        new Timer().schedule(new Task(), initialDelay * 1000, period * 1000);
        Util.sleep();
    }

    
}

class Util{
    /**
     * 间隔1s永久休眠，用于阻塞等待任务完成
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
        rwLock.readLock().lock();// 加读锁
        if (null == cache) {
            rwLock.readLock().unlock();
            rwLock.writeLock().lock();// 加写锁
            cache = "data";
            rwLock.writeLock().unlock();// unlock写锁
            rwLock.readLock().lock();
        }
        rwLock.readLock().unlock();// unlock读锁
        return cache;
    }
}


class RWTreeMap {
    private final Map<String, String> data = new TreeMap<String, String>();
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock r = rwLock.readLock();// 写锁
    private final Lock w = rwLock.writeLock();// 读锁
    
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


class BoundedBuffer { // 有界队列 例子来自jdk 原理同 ArrayBlockingQueue
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
            ++count; // 元素数加1
            notEmpty.signal();// 通知取线程
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
            --count; // 取一个，从0循环取
            notFull.signal(); // 通知添加线程
            return x;
        } finally {
            lock.unlock();
        }
    }
}
