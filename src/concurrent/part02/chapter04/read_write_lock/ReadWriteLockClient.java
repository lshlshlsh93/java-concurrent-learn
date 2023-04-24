package concurrent.part02.chapter04.read_write_lock;

/**
 * @Author lishaohui
 * @Date 2023/4/17 23:50
 */
public class ReadWriteLockClient {

    public static void main(String[] args) {
        final SharedData sharedData = new SharedData(10);

        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new WriteWorker(sharedData,"QWERTYUIOP").start();
        new WriteWorker(sharedData,"qwertyuiop").start();

    }

}
