package concurrent.part02.chapter06.guarded_suspension_design_pattern;

import java.util.Random;

/**
 * @Author lishaohui
 * @Date 2023/4/24 0:53
 */
public class Server extends Thread {

    private final RequestQueue queue;

    private final Random random;

    private volatile boolean closed = false;

    public Server(RequestQueue queue) {
        this.queue = queue;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        while (!closed) {
            Request request = queue.getRequest();
            if (null == request) {
                System.out.println("Received the empty request.");
                continue;
            }
            System.out.println("server ->" + request.getValue());
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void close() {
        this.closed = true;
        this.interrupt();
    }

}
