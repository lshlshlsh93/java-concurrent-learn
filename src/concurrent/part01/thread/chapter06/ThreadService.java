package concurrent.part01.thread.chapter06;

/**
 * @Author lishaohui
 * @Date 2023/3/20 9:55
 */
public class ThreadService {

    private Thread executeThread;

    private boolean isFinished = false;

    /**
     * 开辟线程来执行某个任务
     * @param task 任务
     */
    public void execute(Runnable task) {
        executeThread = new Thread(() -> {
            // 创建守护线程
            Thread daemonT = new Thread(task);
            daemonT.setDaemon(true);
            daemonT.start();
            try {
                daemonT.join();
                isFinished = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executeThread.start();
    }

    /**
     * after mills to showdown
     *
     * @param mills mills
     */
    public void shutdown(long mills) {
        long currentTimestamp = System.currentTimeMillis();
        while (!isFinished) {
            if ((System.currentTimeMillis() - currentTimestamp) >= mills) {
                System.out.println("任务超时，需要结束任务");
                // 打断正在执行的线程，它的守护线程也会被打断
                executeThread.interrupt();
                break;
            }
            try {
                executeThread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("执行线程被打断！");
                break;
            }
        }
        isFinished = false;
    }

}
