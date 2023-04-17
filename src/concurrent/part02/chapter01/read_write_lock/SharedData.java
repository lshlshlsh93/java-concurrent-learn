package concurrent.part02.chapter01.read_write_lock;

import java.util.Arrays;

/**
 * @Author lishaohui
 * @Date 2023/4/17 23:30
 */
public class SharedData {

    private final char[] buffer;

    private final ReadWriteLock lock = new ReadWriteLock();

    public SharedData(int size) {
        this.buffer = new char[size];
        Arrays.fill(this.buffer, '*');
    }

    public char[] read() throws InterruptedException {
        try {
            lock.readLock();
            return this.doRead();
        } finally {
            lock.readUnlock();
        }
    }


    public void write(char ch) throws InterruptedException {
        try {
            lock.writeLock();
            this.doWrite(ch);
        } finally {
            lock.writeUnlock();
        }
    }

    private void doWrite(char ch) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = ch;
            slowly(10L);
        }
    }

    private char[] doRead() {
        char[] newBuffer = new char[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            newBuffer[i] = buffer[i];
        }
        slowly(500L);
        return newBuffer;
    }

    private void slowly(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ignored) {
        }
    }


}
