package concurrent.part02.chapter09.producer_consumer_design_pattern;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author lishaohui
 * @Date 2023/4/24 10:53
 */
public class Consumer extends Thread {

    private static final String CONSUMER_NAME = "CONSUMER-";

    private final MessageQueue messageQueue;

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public Consumer(MessageQueue messageQueue, int sequence) {
        super(CONSUMER_NAME + sequence); // 线程名字
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = messageQueue.take();
                System.out.println(Thread.currentThread().getName() + " 消费消息 " + message.getData());
                Thread.sleep(RANDOM.nextInt(1000));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
