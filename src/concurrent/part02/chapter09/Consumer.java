package concurrent.part02.chapter09;

import java.util.Optional;
import java.util.Random;

/**
 * @Author lishaohui
 * @Date 2023/4/24 10:53
 */
public class Consumer extends Thread {

    private final MessageQueue messageQueue;
    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public Consumer(MessageQueue messageQueue, int seq) {
        super("CONSUMER" + seq);
        this.messageQueue = messageQueue;
    }


    @Override
    public void run() {
        while (true) {
            try {
                Message message = messageQueue.poll();
                Optional.of(Thread.currentThread().getName() + " 消费消息 " + message.getData())
                        .ifPresent(System.out::println);
                Thread.sleep(RANDOM.nextInt(1000));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
