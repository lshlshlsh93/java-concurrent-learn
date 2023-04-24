package concurrent.part02.chapter03.immutable;

/**
 * @Author lishaohui
 * @Date 2023/4/23 23:39
 */
public class ImmutablePerformance {

    public static void main(String[] args) throws InterruptedException {

        long startTime = System.currentTimeMillis();
        SyncObj syncObj = new SyncObj(); // sync 16728
        syncObj.setName("lsh");
//        ImmutableObj immutableObj = new ImmutableObj("lsh"); // immutable 16330

        Thread t1 = new Thread(() -> {
            for (long i1 = 0L; i1 < 10_000_000; i1++) {
                System.out.println(Thread.currentThread().getName() + syncObj);
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            for (long i1 = 0L; i1 < 10_000_000; i1++) {
                System.out.println(Thread.currentThread().getName() + syncObj);
            }
        });
        t2.start();
        t1.join();
        t2.join();
        long endTime = System.currentTimeMillis();
        System.out.println("elapsed time: " + (endTime - startTime));
    }

}


final class ImmutableObj {
    private final String name;

    ImmutableObj(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" + name + ']';
    }
}


class SyncObj {

    private String name;


    public synchronized void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" + name + ']';
    }
}