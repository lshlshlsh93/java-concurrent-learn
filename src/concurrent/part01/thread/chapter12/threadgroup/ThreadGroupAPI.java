package concurrent.part01.thread.chapter12.threadgroup;

import java.util.Optional;
import java.util.OptionalInt;

/**
 * @Author lishaohui
 * @Date 2023/4/11 16:34
 */
public class ThreadGroupAPI {

    //这是main方法,程序的入口
    public static void main(String[] args) {

        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1, "T1") {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10_000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();

        ThreadGroup tg2 = new ThreadGroup(tg1, "TG2");
        Thread t2 = new Thread(tg2, "T2") {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10_000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t2.start();

        Optional.of("activeCount: " + tg1.activeCount()).ifPresent(System.out::println);
        Optional.of("activeGroupCount: " + tg1.activeGroupCount()).ifPresent(System.out::println);

        t2.checkAccess();
//        tg1.destroy();
    }

}
