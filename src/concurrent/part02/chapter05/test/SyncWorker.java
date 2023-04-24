package concurrent.part02.chapter05.test;

import concurrent.part02.chapter05.future_design_pattern.Future;
import concurrent.part02.chapter05.future_design_pattern.FutureService;

/**
 * @Author lishaohui
 * @Date 2023/4/24 0:03
 */
public class SyncWorker {

    //这是main方法,程序的入口
    public static void main(String[] args) throws InterruptedException {
        FutureService futureService = new FutureService();
        Future<String> future = futureService.submit(() -> {
            try {
                Thread.sleep(10_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINISH";
        }, System.out::println);

        System.out.println("=======================");
        System.out.println("do other thing");
        Thread.sleep(1000L);
        System.out.println("=======================");
//        System.out.println(future.get()); // 返回结果
    }

    private static String get() {
        return "FINISH";
    }


}
