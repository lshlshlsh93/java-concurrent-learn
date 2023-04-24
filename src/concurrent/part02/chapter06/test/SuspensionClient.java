package concurrent.part02.chapter06.test;

import concurrent.part02.chapter06.guarded_suspension_design_pattern.Client;
import concurrent.part02.chapter06.guarded_suspension_design_pattern.RequestQueue;
import concurrent.part02.chapter06.guarded_suspension_design_pattern.Server;

/**
 * @Author lishaohui
 * @Date 2023/4/24 1:00
 */
public class SuspensionClient {

    public static void main(String[] args) throws InterruptedException {
        final RequestQueue queue = new RequestQueue();
        new Client(queue, "lsh").start();
        Server server = new Server(queue);
        server.start();

        Thread.sleep(10_000L);
        server.close();

    }
}
