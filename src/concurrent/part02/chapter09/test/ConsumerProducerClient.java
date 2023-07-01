package concurrent.part02.chapter09.test;

import concurrent.part02.chapter09.producer_consumer_design_pattern.Consumer;
import concurrent.part02.chapter09.producer_consumer_design_pattern.MessageQueue;
import concurrent.part02.chapter09.producer_consumer_design_pattern.Producer;

/**
 * @Author lishaohui
 * @Date 2023/4/24 11:02
 */
public class ConsumerProducerClient {

    public static void main(String[] args) {

        final MessageQueue messageQueue = new MessageQueue();

        new Producer(messageQueue, 1).start();
        new Producer(messageQueue, 2).start();
        new Producer(messageQueue, 3).start();

        new Consumer(messageQueue, 1).start();
        new Consumer(messageQueue, 2).start();

    }


}
