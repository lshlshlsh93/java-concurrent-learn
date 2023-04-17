package concurrent.part01.thread.chapter04;

/**
 * 守护线程DaemonThread
 *
 * @Author lishaohui
 * @Date 2023/3/19 13:00
 */
public class DaemonThread {

    //这是main方法,程序的入口
    public static void main(String[] args) throws InterruptedException {

        // new Thread 后处于new状态
        Thread t1 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " running");
                Thread.sleep(100_000L);
                System.out.println(Thread.currentThread().getName() + " done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 将线程转换为守护线程可以通过调用Thread对象的setDaemon(true)方法来实现
        // thread.setDaemon(true)必须在thread.start()之前设置，否则会跑出一个IllegalThreadStateException异常。你不能把正在运行的常规线程设置为守护线程
        t1.setDaemon(true);
        // 调用start方法后处于runnable状态
        // may be  runnable --> running --> dead --> blocked
        t1.start();
        Thread.sleep(5_000L); // JDK1.7
        System.out.println(Thread.currentThread().getName());
    }

}

