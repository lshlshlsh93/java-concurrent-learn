package concurrent.part01.thread.chapter11;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author lishaohui
 * @Date 2023/4/11 15:47
 */
public class CatchThreadException {

    private static final int A = 10;
    private static final int B = 0;

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(5_000L);
                var result = A / B;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        /*
            如何捕获线程运行期间的异常？
                通过Thread类中的setUncaughtExceptionHandler(UncaughtExceptionHandler eh)方法来处理
                注：UncaughtExceptionHandler是一个函数式接口
               Thread.class ---> void uncaughtException(Thread t, Throwable e);
         */
        t1.setUncaughtExceptionHandler((t, e) -> {
            Optional.of(e).ifPresent(System.out::println);
            Optional.of(t).ifPresent(System.out::println);
        });
        t1.start();

    }
}
