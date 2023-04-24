package concurrent.part02.chapter07.test;

import java.util.Random;

/**
 * @Author lishaohui
 * @Date 2023/4/24 1:17
 */
public class ThreadLocalComplexTest {

    private static final ThreadLocal<String> THREAD_LOCAL = ThreadLocal.withInitial(() -> "");

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            THREAD_LOCAL.set("Thread-1");
            try {
                Thread.sleep(RANDOM.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + THREAD_LOCAL.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            THREAD_LOCAL.set("Thread-1");
            try {
                Thread.sleep(RANDOM.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + THREAD_LOCAL.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("=============");
        System.out.println(Thread.currentThread().getName() + " " + THREAD_LOCAL.get());
    }


}
