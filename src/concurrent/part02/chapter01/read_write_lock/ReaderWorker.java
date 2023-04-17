package concurrent.part02.chapter01.read_write_lock;

import java.util.Optional;

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
                Optional.of(Thread.currentThread().getName() + " reads " + String.valueOf(readBuf))
                        .ifPresent(System.out::println);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
