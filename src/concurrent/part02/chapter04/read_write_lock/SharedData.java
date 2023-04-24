package concurrent.part02.chapter04.read_write_lock;


/**
 * @Author lishaohui
 * @Date 2023/4/17 23:30
 */
public class SharedData {

    private final char[] buffer;

    private final ReadWriteLock lock = new ReadWriteLock();

    public SharedData(int size) {
        this.buffer = new char[size];
        for (int i = 0; i < size; i++) {
            this.buffer[i] = '*';
        }
    }

    public char[] read() throws InterruptedException {
        try {
            lock.readLock(); // 加读锁
            return this.doRead();
        } finally {
            lock.readUnlock(); // 释放读锁
        }
    }


    public void write(char ch) throws InterruptedException {
        try {
            lock.writeLock(); // 加写锁
            this.doWrite(ch); // 进行写操作
        } finally {
            lock.writeUnlock(); // 释放写锁
        }
    }

    private void doWrite(char ch) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = ch;
            slowly(10);
        }
    }

    private char[] doRead() {
        char[] newBuffer = new char[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            newBuffer[i] = buffer[i];
        }
        slowly(500);
        return newBuffer;
    }

    private void slowly(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ignored) {
        }
    }


}
