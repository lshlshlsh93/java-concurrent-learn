package concurrent.part01.thread.chapter06;

/**
 * @Author lishaohui
 * @Date 2023/3/20 9:51
 */
public class ThreadCloseForce {

    //这是main方法,程序的入口
    public static void main(String[] args) {

        ThreadService threadService = new ThreadService();
        long startTimestamp = System.currentTimeMillis();
        threadService.execute(() -> {
            // load a very heavy resource.
            try {
                Thread.sleep(5_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadService.shutdown(10_000L);
        long endTimestamp = System.currentTimeMillis();
        System.out.printf("cost : [%d] \n" , endTimestamp - startTimestamp);

    }

}
