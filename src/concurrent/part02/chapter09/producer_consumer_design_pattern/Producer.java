package concurrent.part02.chapter09.producer_consumer_design_pattern;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author lishaohui
 * @Date 2023/4/24 10:54
 */
public class Producer extends Thread {

    private static final String PRODUCER_NAME = "PRODUCER-";

    private final MessageQueue messageQueue;

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    private static final AtomicInteger counter = new AtomicInteger(0);

    public Producer(MessageQueue messageQueue, int seq) {
        super(PRODUCER_NAME + seq);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = new Message("message-" + counter.getAndIncrement());
                messageQueue.put(message);
                Optional.of(Thread.currentThread().getName() + " 放置消息 " + message.getData())
                        .ifPresent(System.out::println);
                Thread.sleep(RANDOM.nextInt(1000));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
