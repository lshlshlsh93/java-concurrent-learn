package concurrent.part02.chapter07.test;

/**
 * @Author lishaohui
 * @Date 2023/4/24 1:13
 */
public class ThreadLocalSimpleTest {

    private static final ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "lsh");


    //这是main方法,程序的入口
    public static void main(String[] args) throws InterruptedException {
        threadLocal.set("lsh");
        Thread.sleep(1_000L);
        String value = threadLocal.get();
        System.out.println(value);
    }

}
