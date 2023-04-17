package concurrent.part01.thread.chapter02.example;

/**
 * @Author lishaohui
 * @Date 2023/3/19 0:06
 */
public class BankPlus {

    private static final int MAX = 50;

    //这是main方法,程序的入口
    public static void main(String[] args) {

//        final TickerWindowRunnable tickerWindowRunnable = new TickerWindowRunnable();

        final Runnable ticketWindow = () -> {
            int index = 1;
            while (index <= MAX) {
                System.out.println(Thread.currentThread() + " number is: " + index++);
            }
        };

        Thread window1 = new Thread(ticketWindow, "one");
        Thread window2 = new Thread(ticketWindow, "two");
        Thread window3 = new Thread(ticketWindow, "three");
        window1.start();
        window2.start();
        window3.start();

    }

}
