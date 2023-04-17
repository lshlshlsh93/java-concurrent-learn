package concurrent.part01.thread.chapter09.producerconsumer;

import java.util.stream.Stream;

/**
 * <p>
 * sleep方法和wait方法的区别
 * </p>
 *
 * @Author lishaohui
 * @Date 2023/4/11 12:23
 */
public class DifferenceOfSleepAndWait {

    private static final Object LOCK = new Object();

    public static void sleepMethod() {
        try {
            System.out.println("The thread " + Thread.currentThread().getName() + " enter");
            Thread.sleep(2_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitMethod() {
        // the following code will cause java.lang.IllegalMonitorStateException
        //        try {
        //            LOCK.wait();
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
        synchronized (LOCK) {
            try {
                System.out.println("The thread " + Thread.currentThread().getName() + " enter");
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Stream.of("T1", "T2").forEach(name -> new Thread(DifferenceOfSleepAndWait::waitMethod, name).start());
    }

}
