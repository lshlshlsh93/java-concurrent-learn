package concurrent.part01.thread.chapter10.myLock.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;


import concurrent.part01.thread.chapter10.myLock.service.Lock;

/**
 * @Author lishaohui
 * @Date 2023/4/11 13:10
 */
public class BooleanLock implements Lock {

    /**
     * <p>
     * The lockStatus is true indicated the lock have be get.
     * </p>
     * <p>
     * The lockStatus is false indicated the lock is free (other thread can get this).
     * </p>
     */
    private boolean lockStatus;

    /**
     * <p>
     * The current running of Thread
     * </p>
     */
    private Thread currentThread;

    /**
     * <p>
     * blocked thread collections
     * </p>
     */
    private final Collection<Thread> BLOCKED_THREAD_COLLECTIONS = new ArrayList<>();

    public BooleanLock() {
        this.lockStatus = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while (lockStatus) {
            BLOCKED_THREAD_COLLECTIONS.add(Thread.currentThread());
            this.wait();
        }
        BLOCKED_THREAD_COLLECTIONS.remove(Thread.currentThread());
        // 释放锁
        this.lockStatus = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void lock(long timeoutMillis) throws InterruptedException, TimeOutException {

        if (timeoutMillis <= 0) lock();
        long hasRemainsMillis = timeoutMillis;
        long endTime = System.currentTimeMillis() + hasRemainsMillis;
        while (lockStatus) {
            if (hasRemainsMillis <= 0)
                throw new TimeOutException("Time out!");
            BLOCKED_THREAD_COLLECTIONS.add(Thread.currentThread());
            this.wait(timeoutMillis);
            hasRemainsMillis = endTime - System.currentTimeMillis();
            Optional.of(Thread.currentThread().getName() + ": " + hasRemainsMillis).ifPresent(System.out::println);
        }
        // 释放锁
        this.lockStatus = true;
        this.currentThread = Thread.currentThread();

    }

    @Override
    public synchronized void unlock() {
        if (Thread.currentThread() == currentThread) {
            this.lockStatus = false;
            Optional.of(Thread.currentThread() + " release the lock monitor.").ifPresent(System.out::println);
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        return Collections.unmodifiableCollection(BLOCKED_THREAD_COLLECTIONS);
    }

    @Override
    public int getBlockThreadSize() {
        return BLOCKED_THREAD_COLLECTIONS.size();
    }
}
