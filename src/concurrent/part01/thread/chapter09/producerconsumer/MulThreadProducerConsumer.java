package concurrent.part01.thread.chapter09.producerconsumer;

import java.util.stream.Stream;

/**
 * <p>
 * 多线程生产者消费者模型
 * </p>
 *
 * @Author lishaohui
 * @Date 2023/4/11 11:24
 */
public class MulThreadProducerConsumer {

    private int i = 0;
    private static final Object LOCK = new Object(); // object monitor
    private volatile boolean isProduced = false; // 标志生产者是否已经生产完成

    public void produce() {
        synchronized (LOCK) {
            // 注意如果这里使用if判断，那么可能出现生产一次消费多次的情况
            while (isProduced) {
                // 生产者如果已经生产就等待消费者去消费
                try {
                    LOCK.wait(); // P操作
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
            System.out.println("produce --> " + i); // 生产者生产数据
            LOCK.notifyAll(); // V操作
            isProduced = true; // 设置为已生产状态
        }
    }

    // notify() 唤醒一个线程
    // notifyAll() 唤醒所有线程

    public void consume() {
        synchronized (LOCK) {
            // 注意如果这里使用if判断，那么可能出现生产一次消费多次的情况
            while (!isProduced) {
                // 生产者如果还没有生产那么消费者就等待
                try {
                    LOCK.wait(); // P操作
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("consume -->" + i);// 消费者消费数据
            LOCK.notifyAll(); // V操作
            isProduced = false; // 设置为未生产

        }
    }


    public static void main(String[] args) {

        MulThreadProducerConsumer producerConsumer = new MulThreadProducerConsumer();

        /*    多个消费者和多个生产者的情况     */
        Stream.of("P1", "P2", "P3").forEach(
                n -> new Thread(() -> {
                    while (true) {
                        producerConsumer.produce();
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start()
        );
        Stream.of("C1", "C2", "C3", "C4").forEach(
                n -> new Thread(() -> {
                    while (true) {
                        producerConsumer.consume();
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start()
        );

    }

}
