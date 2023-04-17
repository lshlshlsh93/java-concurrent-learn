package concurrent.part01.thread.chapter10.myLock;

import java.util.Optional;

/**
 * @Author lishaohui
 * @Date 2023/4/11 13:51
 */
public class SynchronizedProblem {

    public static void main(String[] args) throws InterruptedException {
        new Thread(SynchronizedProblem::run, "T1").start();
        Thread.sleep(1_000L);
        Thread t2 = new Thread(SynchronizedProblem::run, "T2");
        t2.start();
        Thread.sleep(2_000L);
        t2.interrupt();
        Optional.of(t2.isInterrupted()).ifPresent(System.out::println);
    }


    // 当前方法使用的锁是SynchronizedProblem的class锁
    private synchronized static void run() {
        Optional.of(Thread.currentThread() + " is running.").ifPresent(System.out::println);
        while (true) {

        }
    }

}
