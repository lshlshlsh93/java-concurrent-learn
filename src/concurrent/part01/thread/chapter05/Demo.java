package concurrent.part01.thread.chapter05;

/**
 * @Author lishaohui
 * @Date 2023/3/20 8:59
 */
public class Demo {

    //这是main方法,程序的入口
    public static void main(String[] args) throws InterruptedException {
        long startTimeStamp = System.currentTimeMillis();
        Thread t1 = new Thread(new CaptureRunnable("M1", 10_000L));
        Thread t2 = new Thread(new CaptureRunnable("M2", 30_000L));
        Thread t3 = new Thread(new CaptureRunnable("M3", 15_000L));
        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
        long endTimeStamp = System.currentTimeMillis();
        System.out.printf("Save data begin timestamp  is %s, end timestamp is: %s \n", startTimeStamp, endTimeStamp);
    }


}

class CaptureRunnable implements Runnable {

    private final String machineName;

    private final Long spendTime;

    public CaptureRunnable(String machineName, Long spendTime) {
        this.machineName = machineName;
        this.spendTime = spendTime;
    }

    @Override
    public void run() {
        // do the really capture
        try {
            Thread.sleep(spendTime);
            System.out.printf(machineName + " completed capture at timestamp [%s] and successful\n",System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getResult() {
        return machineName + " finish ";
    }
}
