package concurrent.part01.thread.chapter07;

/**
 * @Author lishaohui
 * @Date 2023/3/22 15:44
 */
public class SynchronizedDemo {

    private static final Object LOCK = new Object();

    //这是main方法,程序的入口
    public static void main(String[] args) {

        Runnable runnable = () -> {
            // 同步代码块
            synchronized (LOCK){
                try {
                    Thread.sleep(200_000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);
        t1.start();
        t2.start();
        t3.start();
    }

}
