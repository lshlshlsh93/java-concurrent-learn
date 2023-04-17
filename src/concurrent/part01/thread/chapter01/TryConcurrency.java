package concurrent.part01.thread.chapter01;

import java.util.concurrent.TimeUnit;

/**
 * @Author lishaohui
 * @Date 2023/3/17 15:18
 */
public class TryConcurrency {

    public static void main(String[] args) {

        // 创建线程方式之一
        // 使用匿名内部类方式创建线程并启动线程
        new Thread("READ-THREAD") {
            @Override
            public void run() {
                readDataFromDB();
            }
        }.start();
        new Thread("WRITE-THREAD") {
            @Override
            public void run() {
                writeDataToFile();
            }
        }.start();

    }

    private static void readDataFromDB() {
        try {
            print("begin read data with database");
            TimeUnit.SECONDS.sleep(1L);
            print("read data done and start handle it");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        print("read data handle finish and successfully");
    }

    private static void writeDataToFile() {
        try {
            print("begin write data to file");
            TimeUnit.SECONDS.sleep(5L);
            print("write data done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        print("write data handle finish and successfully");
    }

    private static void print(String msg) {
        System.out.println(msg);
    }

}
