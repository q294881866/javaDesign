package javaSe.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.junit.Test;

public class TaskThreadTest {
    
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
                        System.out.println(Thread.currentThread().getName() + "工作");
                        sp.release();
                        System.err.println("还可进入多少线程：" + sp.availablePermits());
                    } catch (Exception e) {
                    }
                }
            };
            service.execute(runnable);
        }
        
        blocking();
    }
    
    
    public static void blocking() {
        while(true){
            try {
                TimeUnit.SECONDS.sleep(10);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    } 

    @Test// 多次，CyclicBarrier 关注多个线程
    public void cyclicBarrierTest() throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(3);
        final CyclicBarrier cb = new CyclicBarrier(3);
        Runnable runnable = () -> {
            try {
                doSomething(cb);
                cb.await();// 等待都做完
                doSomething(cb);
                cb.await();// 第二次集合
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
        System.out.println("线程：" + Thread.currentThread().getName() + "来了");
        if (2 == cb.getNumberWaiting()) {// 目前已经等待2个，到的是第三个，集合完成
            System.out.println("完成任务："+(++taskCount));
        }
    }
    
    
    @Test
    public void waitAnother() throws Exception {
        final int N = 3;
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(N);
        for (int i = 0; i < N; ++i)
            new Thread(new Worker(start, done)).start();
        start.countDown();// 开始信号
        done.await();// 等待完成
    }

    @Test
    public void workTogether() throws Exception {
        final int N = 3;
        CountDownLatch done = new CountDownLatch(N);
        WorkerSteps bigProject = new WorkerSteps(done, N);
        for (int i = 0; i < N; i++) 
            new Thread(bigProject).start();
        done.await();// 等待完成
    }

    static void doWork(Object o) throws Exception {
        Thread.sleep(new Random().nextInt(10));
        System.out.println("I am single lady" + (null == o ? "" : o));
    }
}
// CountDownLatch 一次，关注一个线程
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
            start.await(); // 阻塞等待信号执行
            TaskThreadTest.doWork(null);// do something
            done.countDown();// 计数减一
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
