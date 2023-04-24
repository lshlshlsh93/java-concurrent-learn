package concurrent.part02.chapter06.guarded_suspension_design_pattern;

import java.util.Random;

/**
 * @Author lishaohui
 * @Date 2023/4/24 0:50
 */
public class Client extends Thread {

    private final RequestQueue queue;

    private final Random random;

    private final String value;

    public Client(RequestQueue queue, String value) {
        this.queue = queue;
        this.value = value;
        this.random = new Random(System.currentTimeMillis());
    }


    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("client -> request " + value);
            queue.putRequest(new Request(value));
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

