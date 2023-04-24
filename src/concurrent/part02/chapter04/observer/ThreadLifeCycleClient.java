package concurrent.part02.chapter04.observer;

import java.util.Arrays;

/**
 * @Author lishaohui
 * @Date 2023/4/17 22:43
 */
public class ThreadLifeCycleClient {

    public static void main(String[] args) {
        new ThreadLifeCycleObserver().concurrentQuery(Arrays.asList("1", "2", "3"));

    }

}
