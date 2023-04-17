package concurrent.part02.chapter01.read_write_lock;

/**
 * @Author lishaohui
 * @Date 2023/4/17 23:17
 */
public class ReadWriteLock {

    /**
     * 当前有几个线程在进行读操作
     */
    private int readingReaders = 0;

    /**
     * 当前有几个线程在进行写操作
     */
    private int writingWriters = 0;

    /**
     * 等待读操作的线程
     */
    private int waitingReaders = 0;

    /**
     * 等待写操作的线程
     */
    private int waitingWriters = 0;

    private boolean preferWriter = true;

    public ReadWriteLock() {
        this(true);
    }

    public ReadWriteLock(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }


    /**
     * 读锁
     *
     * @throws InterruptedException e
     */
    public synchronized void readLock() throws InterruptedException {
        this.waitingReaders++; // 等待的读者加一
        try {
            while (writingWriters > 0 || (preferWriter && waitingWriters > 0)) { // 有等待的写者
                this.wait();
            }
            this.readingReaders++; // 正在进行读操作的读者加一
        } finally {
            this.waitingReaders--; // 等待的读者加一
        }
    }

    /**
     * 释放读锁
     */
    public synchronized void readUnlock() {
        this.readingReaders--; // 正在进行读操作的读者减一
        this.notifyAll();
    }

    /**
     * 写锁
     *
     * @throws InterruptedException e
     */
    public synchronized void writeLock() throws InterruptedException {
        this.waitingWriters++; // 等待的写者加一
        try {
            while (readingReaders > 0 || writingWriters > 0) {
                this.wait();
            }
            this.writingWriters++;
        } finally {
            this.waitingWriters--;
        }
    }

    /**
     * 释放写锁
     */
    public synchronized void writeUnlock() {
        this.waitingWriters--;
        this.notifyAll();
    }

}
