package concurrent.part01.thread.chapter09.producerconsumer;

/**
 * @Author lishaohui
 * @Date 2023/4/11 11:18
 */
public class CommonProducerConsumer {

    private int i = 1;

    final private Object LOCK = new Object();

    private void produce() {
        synchronized (LOCK) {
            System.out.println("p-> " + (i++));
        }
    }

    private void consume() {
        synchronized (LOCK) {
            System.out.println("c-> " + i);
        }
    }


    //这是main方法,程序的入口
    public static void main(String[] args) {
        CommonProducerConsumer producerConsumer = new CommonProducerConsumer();
        new Thread(() -> {
            while (true) {
                producerConsumer.produce();
            }
        }, "Producer").start();
        new Thread(() -> {
            while (true) {
                producerConsumer.consume();
            }
        }, "Consumer").start();
    }
}
