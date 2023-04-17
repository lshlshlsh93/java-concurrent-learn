package concurrent.part01.thread.chapter08;

/**
 * @Author lishaohui
 * @Date 2023/4/11 11:01
 */
public class MyDeadLock {

    private final OtherService otherService;

    public MyDeadLock(OtherService otherService) {
        this.otherService = otherService;
    }

    private final Object lock = new Object();

    public void m1() {
        synchronized (lock) {
            System.out.println("M1");
            otherService.s1();
        }
    }

    public void m2() {
        synchronized (lock) {
            System.out.println("M2");
        }
    }


    public static void main(String[] args) {
        OtherService otherService = new OtherService();
        MyDeadLock myDeadLock = new MyDeadLock(otherService);
        otherService.setMyDeadLock(myDeadLock);
        new Thread(()->{
            while (true){
                myDeadLock.m1();
            }
        },"T1").start();
        new Thread(()->{
            while (true){
                otherService.s2();
            }
        },"T2").start();
    }

}
