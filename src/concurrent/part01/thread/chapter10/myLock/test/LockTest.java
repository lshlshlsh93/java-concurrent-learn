package concurrent.part01.thread.chapter10.myLock.test;

import java.util.Optional;
import java.util.stream.Stream;

import concurrent.part01.thread.chapter10.myLock.service.Lock;
import concurrent.part01.thread.chapter10.myLock.service.impl.BooleanLock;

/**
 * @Author lishaohui
 * @Date 2023/4/11 13:21
 */
public class LockTest {

    private static void work() throws InterruptedException {
        Optional.of(Thread.currentThread().getName() + " is Working.").ifPresent(System.out::println);
        Thread.sleep(40_000L);
    }

    public static void main(String[] args) throws InterruptedException {
        final BooleanLock booleanLock = new BooleanLock();
        Stream
                .of("T1", "T2", "T3", "T4")
                .forEach(threadName -> new Thread(() -> {
                            try {
                                booleanLock.lock(10L);
                                Optional.of("[" + Thread.currentThread().getName() + "] have the lock monitor").ifPresent(System.out::println);
                                work();
                            } catch (Lock.TimeOutException e) {
                                Optional.of("[" + Thread.currentThread().getName() + "] is time out").ifPresent(System.out::println);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                booleanLock.unlock();
                            }
                        }, threadName).start()
                );
    }


}
