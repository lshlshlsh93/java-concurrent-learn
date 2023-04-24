package concurrent.part02.chapter07.thread_local.pattern;

import java.util.stream.IntStream;

/**
 * @Author lishaohui
 * @Date 2023/3/18 0:16
 */
public class Test {

    public static void main(String[] args) {
        IntStream.range(1, 6).forEach(i ->
                new Thread(new ExecutionTask()).start()
        );
    }

}
