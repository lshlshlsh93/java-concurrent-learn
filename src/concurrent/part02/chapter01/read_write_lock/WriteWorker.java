package concurrent.part02.chapter01.read_write_lock;

import java.util.Random;

/**
 * @Author lishaohui
 * @Date 2023/4/17 23:41
 */
public class WriteWorker extends Thread {

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    private final SharedData sharedData;

    private final String filter;

    private int index = 0;

    public WriteWorker(SharedData sharedData, String filter) {
        this.sharedData = sharedData;
        this.filter = filter;
    }


    @Override
    public void run() {
        try {
            while (true) {
                char c = nextChar();
                sharedData.write(c);
                Thread.sleep(RANDOM.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private char nextChar() {
        char ch = filter.charAt(index);
        index++;
        if (index >= filter.length()) {
            index = 0;
        }
        return ch;
    }

}
