package concurrent.part01.thread.chapter04;

import java.util.Optional;

/**
 * @Author lishaohui
 * @Date 2023/3/19 13:35
 */
public class SimpleThreadApi {

    //这是main方法,程序的入口
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            Optional.of("Hello").ifPresent(System.out::println);
            try {
                Thread.sleep(100_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");
        t1.start();
        Optional.of(t1.getName()).ifPresent(System.out::println);
        Optional.of(t1.getId()).ifPresent(System.out::println);
        Optional.of(t1.getPriority()).ifPresent(System.out::println);
    }

}
