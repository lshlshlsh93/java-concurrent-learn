package concurrent.part02.chapter05.future_design_pattern;

import java.util.function.Consumer;

/**
 * @Author lishaohui
 * @Date 2023/4/24 0:07
 */
public class FutureService {

    public <T> Future<T> submit(final FutureTask<T> task) {
        AsyncFuture<T> asyncFuture = new AsyncFuture<>();
        new Thread(() -> {
            T result = task.call();
            asyncFuture.done(result);
        }).start();
        return asyncFuture;
    }

    /**
     * @param task     task
     * @param consumer java8 consumer
     * @param <T>      .
     * @return .
     */
    public <T> Future<T> submit(final FutureTask<T> task, final Consumer<T> consumer) {
        AsyncFuture<T> asyncFuture = new AsyncFuture<>();
        new Thread(() -> {
            T result = task.call();
            asyncFuture.done(result);
            consumer.accept(result);
        }).start();
        return asyncFuture;
    }


}
