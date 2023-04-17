package concurrent.part01.thread.chapter07;

/**
 * @Author lishaohui
 * @Date 2023/3/19 0:04
 */
public class TickerWindowRunnable implements Runnable {

    /**
     * runnable接口类似于设计模式中的策略模式 可以把线程控制与业务代码分离抽取出来，单一原则
     */

    public static final int MAX = 500;

    private int index = 1;

    private final Object MONITOR = new Object();

    @Override
    public void run() {

        while (true){
            synchronized (MONITOR) {
                if (index > MAX)
                    break;
                try {
                    Thread.sleep(5L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + " number is: " + index++);
            }
        }

    }

}
