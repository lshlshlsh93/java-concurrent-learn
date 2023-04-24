package concurrent.part02.chapter13;

import java.util.Random;

/**
 * @Author lishaohui
 * @Date 2023/4/24 22:27
 */
public class Transport extends Thread {

    private final Channel channel;

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public Transport(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }


    @Override
    public void run() {
        try {
            for (int i=0;true;i++){
                Request request = new Request(getName(),i);
                this.channel.put(request);
                Thread.sleep(RANDOM.nextInt(1_000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
