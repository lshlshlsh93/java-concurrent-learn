package concurrent.part01.thread.chapter05;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @Author lishaohui
 * @Date 2023/3/20 8:36
 */
public class ThreadJoin {

    //这是main方法,程序的入口
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            IntStream.range(1, 1000).forEach(i -> {
                System.out.println(Thread.currentThread().getName() + " " + i);
            });
        });
        Thread t2 = new Thread(() -> {
            IntStream.range(1, 1000).forEach(i -> {
                System.out.println(Thread.currentThread().getName() + " " + i);
            });
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        Optional.of("ALL of the finish done").ifPresent(System.out::println);

        IntStream.range(1, 1000).forEach(i -> {
            System.out.println(Thread.currentThread().getName() + " " + i);
        });
    }

}
