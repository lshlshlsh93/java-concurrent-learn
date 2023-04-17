package concurrent.part01.thread.chapter03;

/**
 * @Author lishaohui
 * @Date 2023/3/19 0:45
 */
public class CreateThread {


    public static void main(String[] args) {

        // Thread()
        Thread t1 = new Thread();
        Thread t2 = new Thread(() -> System.out.println("++++++"));
        t1.start();
        t2.start();
        System.out.println(t1.getName());
        System.out.println(t2.getName());

        // Thread(String name)
        Thread t3 = new Thread("myName");

        // new Thread(Runnable target)
        Thread t4 = new Thread(()->{
            System.out.println("runnable");
        });
        System.out.println(t3.getName());
        System.out.println(t4.getName());

        // Thread(Runnable target, String name)
        Thread t5 = new Thread(()->{
            System.out.println("runnable --- " + Thread.currentThread().getName());
        },"Runnable-Thread");
        t5.start();

    }


}
