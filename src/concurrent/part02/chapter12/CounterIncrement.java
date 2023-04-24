package concurrent.part02.chapter12;

import java.util.Random;

/**
 * @Author lishaohui
 * @Date 2023/4/24 11:36
 */
public class CounterIncrement extends Thread {

    private volatile boolean terminated = false;

    private int counter = 0;

    private static final Random random = new Random(System.currentTimeMillis());

    @Override
    public void run() {
        try {
            while (!terminated) {
                System.out.println(Thread.currentThread().getName() + " " + counter++);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            // e.printStackTrace();
        } finally {
            this.clean();
        }
    }

    private void clean() {
        System.out.println("do something clean work for the second phase. current counter" + counter);
    }

    public void close() {
        this.terminated = true;
        this.interrupt();
    }
}
