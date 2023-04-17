package concurrent.part01.thread.chapter08;

/**
 * @Author lishaohui
 * @Date 2023/4/11 11:01
 */
public class OtherService {

    private final Object lock = new Object();

    private MyDeadLock myDeadLock;

    public void setMyDeadLock(MyDeadLock myDeadLock) {
        this.myDeadLock = myDeadLock;
    }

    public void s1() {
        synchronized (lock) {
            System.out.println("s1=======");
        }
    }

    public void s2() {
        synchronized (lock) {
            myDeadLock.m2();
            System.out.println("s2=======");
        }
    }

}
