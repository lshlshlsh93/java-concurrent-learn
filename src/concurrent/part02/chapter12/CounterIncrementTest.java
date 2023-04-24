package concurrent.part02.chapter12;

/**
 * @Author lishaohui
 * @Date 2023/4/24 11:40
 */
public class CounterIncrementTest {

    public static void main(String[] args) throws InterruptedException {
        CounterIncrement increment = new CounterIncrement();
        increment.start();

        Thread.sleep(10_000L);
        increment.close();
    }

}
