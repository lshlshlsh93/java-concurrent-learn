package concurrent.part01.thread.chapter09.producerconsumer;

import java.util.stream.Stream;

/**
 * <p>
 * 生产者消费者模型
 * </p>
 *
 * @Author lishaohui
 * @Date 2023/4/11 11:24
 */
public class SingleProducerConsumer {


    /*
        操作系统中的同步与互斥问题
        典型的就是生产者消费者问题
            通过PV原语
            或
            wait/signal原语
     */

    private int i = 0;

    private static final Object LOCK = new Object();

    private volatile boolean isProduced = false;

    public void produce() {
        synchronized (LOCK) {
            if (isProduced) { // 生产者如果已经生产就等待消费者去消费
                try {
                    LOCK.wait(); // P操作
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                i++;
                System.out.println("produce --> " + i); // 生产
                LOCK.notify(); // V操作
                isProduced = true; // 设置为已生产状态
            }

        }
    }


    public void consume() {
        synchronized (LOCK) {
            if (isProduced) {
                System.out.println("consume -->" + i);// 消费
                LOCK.notify(); // V操作
                isProduced = false; // 设置为未生产
            } else { // 生产者如果还没有生产那么消费者就等待
                try {
                    LOCK.wait(); // P操作
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        SingleProducerConsumer producerConsumerPlus = new SingleProducerConsumer();

        /* 单个消费者和单个生产者的情况*/
/*        new Thread(() -> {
            while (true) {
                producerConsumerPlus.produce();
            }
        }, "Produce").start();

        new Thread(() -> {
            while (true) {
                producerConsumerPlus.consume();
            }
        }, "Consumer").start();*/



        /*    多个消费者和多个生产者的情况     */
        Stream.of("Producer1", "Producer2").forEach(
                n -> new Thread(() -> {
                    for (; ; ) {
                        producerConsumerPlus.produce();
                    }
                }).start()
        );
        Stream.of("Consumer1", "Consumer2").forEach(
                n -> new Thread(() -> {
                    for (; ; ) {
                        producerConsumerPlus.consume();
                    }
                }).start()
        );

    }

}
