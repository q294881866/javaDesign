package javaSe.concurrent.example;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class NumberPrinter {
    private volatile int start;
    private volatile int end;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public NumberPrinter(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public boolean isPrime(int num) {
        if (num == 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        NumberPrinter numberPrinter = new NumberPrinter(1, 100);
        System.out.println(numberPrinter.isPrime(99));
    }

    public void printPrime() {
        try {
            lock.lock();
            while (start < end) {
                System.out.println("质数:::" + start);
                while (!isPrime(start) && start < end) {
                    System.out.println("质数--等待" + start);
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + " 质数: " + start);
                start++;
                condition.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printOther() {
        try {
            lock.lock();
            while (start < end) {
                System.out.println("其他:::" + start);
                while (isPrime(start) && start < end) {
                    System.out.println("其他--等待" + start);
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + " 非质数: " + start);
                start++;
                condition.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class RunableA implements Runnable {
    private NumberPrinter numberPrinter;

    public RunableA(NumberPrinter numberPrinter) {
        this.numberPrinter = numberPrinter;
    }

    @Override
    public void run() {
        numberPrinter.printPrime();
    }
}

class RunnableB implements Runnable {
    private NumberPrinter numberPrinter;

    public RunnableB(NumberPrinter numberPrinter) {
        this.numberPrinter = numberPrinter;
    }

    @Override
    public void run() {
        numberPrinter.printOther();
    }
}

class Test {
    public static void main(String[] args) {
        NumberPrinter numberPrinter = new NumberPrinter(1, 259);

        Thread threadA = new Thread(new RunableA(numberPrinter), "A");
        Thread threadB = new Thread(new RunnableB(numberPrinter), "B");
        threadA.start();
        threadB.start();
    }
}

class Str2NumUtil {
    public static int str2num(String str) {
        if (!str.matches("^-?\\d+$")) {
            throw new IllegalArgumentException("字符串不能解析成数字");
        }
        return Integer.parseInt(str);
    }

    public static void main(String[] args) {
        System.out.println(str2num("-123.01"));
    }
}
