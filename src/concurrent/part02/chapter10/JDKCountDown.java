package concurrent.part02.chapter10;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * @Author lishaohui
 * @Date 2023/4/24 11:07
 */
public class JDKCountDown {

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

        final CountDownLatch countDownLatch = new CountDownLatch(5);

        System.out.println("准备多线程处理任务");
        // first phase
        IntStream.rangeClosed(1, 5)
                .forEach(i ->
                        new Thread(() -> {
                            System.out.println(Thread.currentThread().getName() + " is working ");
                            try {
                                Thread.sleep(RANDOM.nextInt(1000));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            countDownLatch.countDown();
                        }, String.valueOf(i)).start()
                );
        countDownLatch.await();
        // second phase
        System.out.println("多线程处理任务全部结束，准备第二阶段任务");
        System.out.println("FINISH");
        System.out.println("..........");

    }

}
