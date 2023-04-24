package concurrent.part02.chapter10;

import java.util.Random;

import static java.util.stream.IntStream.*;

/**
 * @Author lishaohui
 * @Date 2023/4/24 11:17
 */
public class CustomCountDownClient {

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

        final SelfDesignCountDown countDown = new SelfDesignCountDown(5);
        rangeClosed(1, 5)
                .forEach(i -> new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + " is working ");
                    try {
                        Thread.sleep(RANDOM.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    countDown.countDown();
                }, String.valueOf(i)).start());
        countDown.await();
        // second phase
        System.out.println("多线程处理任务全部结束，准备第二阶段任务");
        System.out.println("FINISH");
        System.out.println("..........");
    }

}
