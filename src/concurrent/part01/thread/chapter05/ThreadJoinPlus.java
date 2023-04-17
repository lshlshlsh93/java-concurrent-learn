package concurrent.part01.thread.chapter05;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @Author lishaohui
 * @Date 2023/3/20 8:50
 */
public class ThreadJoinPlus {

    public static void main(String[] args) throws InterruptedException {

//        Thread t1 = new Thread(() -> {
//            try {
//                System.out.println("t1 is running");
//                Thread.sleep(10_000L);
//                System.out.println("t1 is done");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        t1.start();
////        t1.join(100);
////        t1.join(100,10);
//        Optional.of("ALL of the finish done").ifPresent(System.out::println);
//
//        IntStream.range(1, 1000).forEach(i -> {
//            System.out.println(Thread.currentThread().getName() + " " + i);
//        });


        // Thread.currentThread().join();

    }

}
