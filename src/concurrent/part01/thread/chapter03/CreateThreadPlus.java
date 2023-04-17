package concurrent.part01.thread.chapter03;

import java.util.Arrays;

/**
 * @Author lishaohui
 * @Date 2023/3/19 1:08
 */
public class CreateThreadPlus {

    public static void main(String[] args) {
         Thread t1 = new Thread(() -> {
             try {
                 Thread.sleep(1000L);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         });
         t1.start();

        // Thread(ThreadGroup group, Runnable target)
        new Thread(null,()->{
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // Thread(ThreadGroup group, Runnable target, String name)
        new Thread(null,()->{
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"test.Test-Thread").start();

        // Thread(ThreadGroup group, Runnable target, String name,long stackSize)
        // stackSize默认是0，0代表着会忽略该参数
        new Thread(null,()->{
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"test.Test-Thread",10000L).start();

        // since 9
        // Thread(ThreadGroup group, Runnable target, String name,long stackSize, boolean inheritThreadLocals)
        new Thread(null,()->{
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"test.Test-Thread",10000L,false).start();

//        System.out.println(t1.getThreadGroup());
//        System.out.println(Thread.currentThread().getName());
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
//        System.out.println(threadGroup.getName());
        System.out.println(threadGroup.activeCount());

        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        /*
        Thread[main,5,main]
        Thread[Monitor Ctrl-Break,5,main]
        Thread[Thread-0,5,main]
         */
        Arrays.asList(threads).forEach(System.out::println);
    }
}
