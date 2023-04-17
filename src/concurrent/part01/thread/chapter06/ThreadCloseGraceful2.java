package concurrent.part01.thread.chapter06;

/**
 * @Author lishaohui
 * @Date 2023/3/20 9:46
 */
public class ThreadCloseGraceful2 {

    // 优雅的方式结束线程 方式2
    // 通过interrupt方式
    // 通过isInterrupted()方式判断是否被打断
    private static class Worker extends Thread {

        @Override
        public void run() {
            while (true) {
                if (isInterrupted()) {
                    break;
                }
            }
        }
    }

    //这是main方法,程序的入口
    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();
        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.interrupt();
    }

}
