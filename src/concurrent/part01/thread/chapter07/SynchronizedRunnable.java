package concurrent.part01.thread.chapter07;

/**
 * @Author lishaohui
 * @Date 2023/3/22 15:59
 */
public class SynchronizedRunnable implements Runnable {

    // read-only
    public static final int MAX = 500;

    private int index = 1;

    @Override
    public void run() {
        while (true){
            if (ticket())
                break;
        }
    }

    private synchronized boolean ticket() {
        // get Field
        if (index > MAX)
            return true;
        try {
            Thread.sleep(5L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // get field
        // index = index + 1;
        // put field index
        System.out.println(Thread.currentThread() + " number is: " + index++);
        return false;
    }

}
