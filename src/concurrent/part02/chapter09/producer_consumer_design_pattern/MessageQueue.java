package concurrent.part02.chapter09.producer_consumer_design_pattern;

import java.util.LinkedList;

/**
 * @Author lishaohui
 * @Date 2023/4/24 10:44
 */
public class MessageQueue {

    private final LinkedList<Message> messageQueue;

    public static final int DEFAULT_MAX_LIMIT = 100;

    private final int limit;

    public MessageQueue() {
        this(DEFAULT_MAX_LIMIT);
    }

    public MessageQueue(final int limit) {
        this.limit = limit;
        this.messageQueue = new LinkedList<>();
    }

    public void put(final Message message) throws InterruptedException {
        synchronized (messageQueue) {
            while (messageQueue.size() > limit) {
                messageQueue.wait();
            }
            messageQueue.addLast(message);
            messageQueue.notifyAll();
        }
    }

    public Message take() throws InterruptedException {
        synchronized (messageQueue) {
            while (messageQueue.isEmpty()) {
                messageQueue.wait();
            }
            Message message = messageQueue.removeFirst();
            messageQueue.notifyAll();
            return message;
        }
    }

    public int getMaxLimit() {
        return this.limit;
    }

    public int getMessageSize() {
        synchronized (messageQueue) {
            return messageQueue.size();
        }
    }
}
