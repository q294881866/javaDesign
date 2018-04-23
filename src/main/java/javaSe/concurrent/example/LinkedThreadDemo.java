package javaSe.concurrent.example;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;


/**
 * 测试题
 * <p>
 * A，B两个线程按序输出0-100，A输出质数，B输出其它
 * <p>
 * 利用Locksuppot挂起和唤醒
 * 
 * @author ppf@jiumao.org
 * @date 2017年9月25日
 */
public class LinkedThreadDemo implements Runnable {
    final Set<Integer> primeNumber = new HashSet<>();

    int end;
    volatile int i;
    Node exec;

    public LinkedThreadDemo(int begin, int end) {
        super();
        this.i = begin;
        this.end = end;
    }



    public static void main(String[] args) throws Exception {

        LinkedThreadDemo demo = new LinkedThreadDemo(0, 100);

        Thread a = new Thread(demo, "A");
        Node A = new Node(true, a);
        Thread b = new Thread(demo, "B");
        Node B = new Node(false, b);

        A.next = B;
        B.next = A;
        demo.exec = A;

        a.start();
        b.start();
        for (;;) {
            TimeUnit.SECONDS.sleep(1);
        }
    }


    @Override
    public void run() {
        for (; i < end + 1;) {
            if (Thread.currentThread() == exec.thread && MathUtil.isPrime(i, primeNumber) == exec.status) {// A线程
                System.out.println(Thread.currentThread().getName() + " : " + i);
                i++;
            } else if (Thread.currentThread() == exec.next.thread && MathUtil.isPrime(i, primeNumber) == exec.next.status) {// B线程
                System.out.println(Thread.currentThread().getName() + " : " + i);
                i++;
            } else {// 状态不匹配
                Thread unpark = Thread.currentThread() == exec.thread ? exec.next.thread : exec.thread;
                LockSupport.unpark(unpark);
                LockSupport.park();
            }
        }
    }

}


