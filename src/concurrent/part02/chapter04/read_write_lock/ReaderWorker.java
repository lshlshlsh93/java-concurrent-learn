package concurrent.part02.chapter04.read_write_lock;

/**
 * @Author lishaohui
 * @Date 2023/4/17 23:47
 */
public class ReaderWorker extends Thread {

    private final SharedData sharedData;

    public ReaderWorker(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char[] readBuf = sharedData.read();
                System.out.println(Thread.currentThread().getName() + " reads " + String.valueOf(readBuf));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
