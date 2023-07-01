package concurrent.part02.chapter13.worker_thread_design_pattern;

import java.util.Random;

/**
 * @Author lishaohui
 * @Date 2023/4/24 22:17
 */
public class Worker extends Thread {

    private final Channel channel;

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public Worker(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }


    @Override
    public void run() {
        while (true) {
            channel.poll().execute();
            try {
                Thread.sleep(RANDOM.nextInt(1_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
