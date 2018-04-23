package javaSe.concurrent.example;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * 测试题
 * <p>
 * A，B两个线程按序输出0-100，A输出质数，B输出其它
 * <P>
 * 使用 隐式队列 每次都唤醒所有线程同时执行，不符合条件的线程等待。
 * 
 * @author ppf@jiumao.org
 * @date 2017年9月25日
 */
public class ObjectLockDemo implements Runnable {
    final Set<Integer> primeNumber = new HashSet<>();
    int end;
    volatile int i;
    Node exec;
    Object lock = new Object();


    public ObjectLockDemo(int begin, int end) {
        super();
        this.i = begin;
        this.end = end;
    }


    public static void main(String[] args) throws Exception {

        ObjectLockDemo demo = new ObjectLockDemo(0, 100);

        Thread a = new Thread(demo, "A");
        Node A = new Node(true, a);
        Thread b = new Thread(demo, "B");
        Node B = new Node(false, b);

        A.next = B;
        demo.exec = A;

        a.start();
        b.start();
        for (;;) {
            TimeUnit.SECONDS.sleep(1);
        }
    }


    @Override
    public void run() {
        synchronized (lock) {
            for (; i < end + 1;) {
                if (Thread.currentThread() == exec.thread && MathUtil.isPrime(i, primeNumber) == exec.status) {// A
                    // 线程
                    System.out.println(Thread.currentThread().getName() + " : " + i);
                    i++;
                } else if (Thread.currentThread() == exec.next.thread && MathUtil.isPrime(i, primeNumber) == exec.next.status) {// B
                    // 线程
                    System.out.println(Thread.currentThread().getName() + " : " + i);
                    i++;
                } else {// 状态不匹配
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notifyAll();
            }
        }
    }

}


class Node {
    boolean status;
    Thread thread;
    Node next;


    public Node(boolean prime, Thread thread) {
        super();
        this.status = prime;
        this.thread = thread;
    }

}
