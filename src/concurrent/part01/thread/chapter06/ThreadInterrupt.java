package concurrent.part01.thread.chapter06;

/**
 * @Author lishaohui
 * @Date 2023/3/20 9:13
 */
public class ThreadInterrupt {

    private static final Object MONITOR = new Object();

    //这是main方法,程序的入口
    public static void main(String[] args) throws InterruptedException {
//        Thread t1 = new Thread() {
//            @Override
//            public void run() {
//               while (true){
//                   synchronized (MONITOR){
//                       try {
//                           MONITOR.wait(10);
//                       } catch (InterruptedException e) {
//                           e.printStackTrace();
//                           System.out.println(isInterrupted());
//                       }
//                   }
//               }
//            }
//        };
//        t1.start();
//        Thread.sleep(100);
//        System.out.println(t1.isInterrupted()); // false
//        t1.interrupt();
//        System.out.println(t1.isInterrupted()); // true


        Thread t1 = new Thread(() -> {
              while (true){
                  synchronized (MONITOR){
                      try {
                          MONITOR.wait(10);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                          System.out.println(Thread.interrupted());
                      }
                  }
              }
        });
        t1.start();


    }
}