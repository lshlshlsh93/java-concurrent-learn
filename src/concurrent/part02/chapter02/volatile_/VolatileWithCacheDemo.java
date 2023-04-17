package concurrent.part02.chapter02.volatile_;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author lishaohui
 * @Date 2023/4/16 23:24
 */
public class VolatileWithCacheDemo {

    private static volatile int INITIAL_VALUE = 0;

    private static final int MAX_LIMIT = 5;


    public static void main(String[] args) {

        new Thread(() -> {
            while (INITIAL_VALUE < MAX_LIMIT) {
                System.out.println("T1-- " + (++INITIAL_VALUE));
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "reader").start();

        new Thread(() -> {
            while (INITIAL_VALUE < MAX_LIMIT) {
                System.out.println("T2-- " + (++INITIAL_VALUE));
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "writer").start();

    }

}
