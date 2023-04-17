package concurrent.part01.thread.chapter06;

/**
 * @Author lishaohui
 * @Date 2023/3/20 9:36
 */
public class ThreadCloseGraceful {

    // 如何优雅的方式结束线程 方式1
    // 利用开关的方式
    private static class Worker extends Thread {

        private volatile boolean start = true;

        @Override
        public void run() {
            while (start) {
                // do something

            }
        }

        public void shutdown() {
            this.start = false;
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
        worker.shutdown();
    }

}
