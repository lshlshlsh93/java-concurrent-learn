package concurrent.part01.thread.chapter07;


/**
 * @Author lishaohui
 * @Date 2023/3/18 23:37
 */
public class Bank {


    /**
     * 多线程方式模拟银行挂号
     */
    //这是main方法,程序的入口
    public static void main(String[] args) {

        final SynchronizedRunnable ticketWindow = new SynchronizedRunnable();

        Thread window1 = new Thread(ticketWindow, "one");
        Thread window2 = new Thread(ticketWindow, "two");
        Thread window3 = new Thread(ticketWindow, "three");
        window1.start();
        window2.start();
        window3.start();
    }

}
