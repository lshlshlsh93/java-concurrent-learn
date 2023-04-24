package concurrent.part02.chapter11;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author lishaohui
 * @Date 2023/4/24 11:23
 */
public class MessageHandler {

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    private static final Executor EXECUTOR = Executors.newFixedThreadPool(5);

    public void request(Message message) {
        EXECUTOR.execute(() -> {
            String value = message.getValue();
            try {
                Thread.sleep(RANDOM.nextInt(1000));
                System.out.println("The message will be handler by  " + Thread.currentThread().getName() + " " + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void shutdown(){
        ((ExecutorService)EXECUTOR).shutdown();
    }

}
