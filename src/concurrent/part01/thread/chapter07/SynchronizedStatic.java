package concurrent.part01.thread.chapter07;

/**
 * @Author lishaohui
 * @Date 2023/4/11 10:51
 */
public class SynchronizedStatic {

    /*  静态代码块中加的锁就是class锁 */
    static {
        synchronized (SynchronizedStatic.class) {
            try {
                System.out.println("Static " + Thread.currentThread().getName());
                Thread.sleep(10_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static void m1() {
        System.out.println("m1 " + Thread.currentThread().getName());
        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void m2() {
        System.out.println("m2 " + Thread.currentThread().getName());
        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void m3() {
        System.out.println("m3 " + Thread.currentThread().getName());
        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //这是main方法,程序的入口
    public static void main(String[] args) {
        new Thread(SynchronizedStatic::m1, "T1").start();
        new Thread(SynchronizedStatic::m2, "T2").start();
        new Thread(SynchronizedStatic::m3, "T3").start();
    }

}
