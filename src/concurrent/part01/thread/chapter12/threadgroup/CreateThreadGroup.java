package concurrent.part01.thread.chapter12.threadgroup;

import java.util.Arrays;
import java.util.Optional;

/**
 * @Author lishaohui
 * @Date 2023/4/11 16:14
 */
public class CreateThreadGroup {


    public static void main(String[] args) {

        // use the name
        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1, "T1") {
            @Override
            public void run() {
                while (true) {
                    try {
                        Optional.of(getThreadGroup().getName()).ifPresent(System.out::println);
                        Optional.of(getThreadGroup().getParent().activeCount()).ifPresent(System.out::println);
                        Optional.of(getThreadGroup().getParent().getMaxPriority()).ifPresent(System.out::println);
                        Thread.sleep(10_000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();


        // use the parent and group name
        ThreadGroup tg2 = new ThreadGroup("TG2");
        Thread t2 = new Thread(tg2, "T2") {
            @Override
            public void run() {
                Optional.of("tg1 name: " + tg1.getName()).ifPresent(System.out::println);
                Thread[] threads = new Thread[tg1.activeCount()];
                tg1.enumerate(threads);
                Arrays.asList(threads).forEach(System.out::println);
            }
        };
        t2.start();

        Optional.of(Thread.currentThread().getName()).ifPresent(System.out::println);
        Optional.of(Thread.currentThread().getThreadGroup().getName()).ifPresent(System.out::println);

    }

}
