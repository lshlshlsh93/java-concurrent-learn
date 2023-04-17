package concurrent.part02.chapter02.volatile_;

/**
 * @Author lishaohui
 * @Date 2023/4/16 23:06
 */
public class VolatileDemo {

    private static volatile int INITIAL_VALUE = 0;

    private static final int MAX_LIMIT = 5;

    public static void main(String[] args) {

        new Thread(() -> {

            int value = INITIAL_VALUE;
            while (value < MAX_LIMIT) {
                if (value != INITIAL_VALUE) {
                    System.out.printf("the value update to [%d]\n", INITIAL_VALUE);
                    value = INITIAL_VALUE;
                }
            }
        }, "reader").start();

        new Thread(() -> {
            int value = INITIAL_VALUE;
            while (INITIAL_VALUE < MAX_LIMIT) {
                System.out.printf("update the value to [%d]\n", ++value);
                INITIAL_VALUE = value;
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "writer").start();

    }

}
