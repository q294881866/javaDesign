package javaSe.concurrent;

import java.sql.Connection;


public class ThreadLocalTest {

    private static ThreadLocal<Integer> count = new ThreadLocal<>();
    private static ThreadLocal<Connection> con = new ThreadLocal<>();


    public static void main(String[] args) {

        count.set(0);
        count.get();
    }

}
