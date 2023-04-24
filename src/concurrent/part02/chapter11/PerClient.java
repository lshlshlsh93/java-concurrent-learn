package concurrent.part02.chapter11;

import java.util.stream.IntStream;

/**
 * @Author lishaohui
 * @Date 2023/4/24 11:26
 */
public class PerClient {

    public static void main(String[] args) {
        final MessageHandler handler = new MessageHandler();
        IntStream.rangeClosed(0, 10).forEach(
                i -> handler.request(new Message(String.valueOf(i)))
        );
        handler.shutdown();
    }
}
