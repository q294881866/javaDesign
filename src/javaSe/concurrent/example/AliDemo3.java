package javaSe.concurrent.example;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 测试题
 * <p>
 * A，B两个线程按序输出0-100，A输出质数，B输出其它
 * <P>
 * 利用condition，切换线程等待队列
 * @author ppf@jiumao.org
 * @date 2017年9月25日
 */
public class AliDemo3 {
    
    final static Set<Integer> PrimeNumber = new HashSet<>();

    public static boolean isPrime(int c) {
        if (0 == c)
            return false;
        if (1 == c)
            return true;

        if (PrimeNumber.contains(c)) {
            return true;
        }
        for (Integer prime : PrimeNumber) {
            if (c % prime == 0) {
                return false;
            }
        }
        PrimeNumber.add(c);
        return true;
    }


    public static void main(String[] args) throws InterruptedException {
        final Lock lock = new ReentrantLock();
        final Condition prime = lock.newCondition();
        final Condition composite = lock.newCondition();
        final  AtomicInteger count = new AtomicInteger(0);
        new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 101;i = count.get()) {
                    if (isPrime(i)) {
                        System.out.println(Thread.currentThread().getName() + " : " + i);
                        count.incrementAndGet();
                    }
                    else {
                        composite.signal();
                        prime.await();
                    }
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }

        }, "A").start();
        
        
        new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 101;i = count.get()){
                    if (!isPrime(i)) {
                        System.out.println(Thread.currentThread().getName() + " : " + i);
                        count.incrementAndGet();
                    }
                    else {
                        prime.signal();
                        composite.await();
                    }
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }

        }, "B").start();
        
        for(;;){
            TimeUnit.SECONDS.sleep(1);
        }

    }

}
