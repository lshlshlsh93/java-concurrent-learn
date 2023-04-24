package concurrent.part02.chapter12.exe;

import java.io.IOException;

/**
 * @Author lishaohui
 * @Date 2023/4/24 12:05
 */
public class Client {

    public static void main(String[] args) throws InterruptedException, IOException {

        Server server = new Server(1222);
        server.start();
        Thread.sleep(45_000L);
        server.shutdown();
    }

}
