package concurrent.part01.thread.chapter10.myLock.service;

import java.util.Collection;

/**
 * @Author lishaohui
 * @Date 2023/4/11 13:06
 */
public interface Lock {

    class TimeOutException extends Exception {
        public TimeOutException(String message) {
            super(message);
        }
    }

    /**
     * 加锁
     * @throws InterruptedException 打断异常
     */
    void lock() throws InterruptedException;

    /**
     * 加锁附加超时时间
     *
     * @param timeoutMillis 超时时间
     * @throws InterruptedException 打断异常
     * @throws TimeOutException     超时异常
     */
    void lock(long timeoutMillis) throws InterruptedException, TimeOutException;

    void unlock();

    Collection<Thread> getBlockedThread();

    int getBlockThreadSize();

}
