package concurrent.part02.chapter02;


/**
 * @Author lishaohui
 * @Date 2023/4/13 0:34
 */
public class WaitAndSet {

    /**
     * <p> 1. 所有的对象都会有一个wait set，用来存放调用了该对象wait方法之后进入block状态线程</p>
     * <p>2. 线程被notify之后，不一定立即得到执行</p>
     * <p>3. 线程从wait set中被唤醒顺序不一定是FIFO。</p>
     * <p>4. 线程被唤醒后必须重新获取锁。</p>
     */

    public static final Object LOCK = new Object();

    public static void work() {
        synchronized (LOCK) {
            System.out.println("Beginning");
            try {
                System.out.println("The thread will coming");
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The thread will end ");
        }
    }

    public static void main(String[] args) {

//        IntStream
//                .rangeClosed(1, 10)
//                .forEach(i -> {
//                    new Thread(String.valueOf(i)) {
//                        @Override
//                        public void run() {
//                            synchronized (LOCK) {
//                                try {
//                                    Optional.of(Thread.currentThread().getName() + " will come to wait set.").ifPresent(System.out::println);
//                                    LOCK.wait();
//                                    Optional.of(Thread.currentThread().getName() + " will leave to wait set.").ifPresent(System.out::println);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                    }.start();
//                });
//
//        try {
//            Thread.sleep(3_000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        IntStream.rangeClosed(1, 10).forEach(i ->
//                {
//                    synchronized (LOCK) {
//                        LOCK.notify();
//                        try {
//                            Thread.sleep(1_000L);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }

//        );


    }

}
