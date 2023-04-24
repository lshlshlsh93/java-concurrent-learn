package concurrent.part02.chapter04.observer;

import java.util.List;
import java.util.Optional;

/**
 * @Author lishaohui
 * @Date 2023/4/17 22:33
 */
public class ThreadLifeCycleObserver implements LifeCycleListener {

    private final Object LOCK = new Object();

    public void concurrentQuery(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        ids.stream().forEach(id -> new Thread(new ObservableRunnable(this) {
            @Override
            public void run() {
                try {
                    notifyChange(new RunnableEvent(RunnableState.RUNNING, Thread.currentThread(), null));
                    System.out.println("query for the id: " + id);
                    Thread.sleep(1_000L);
                    notifyChange(new RunnableEvent(RunnableState.DONE, Thread.currentThread(), null));
                } catch (Exception e) {
                    notifyChange(new RunnableEvent(RunnableState.ERROR, Thread.currentThread(), e));
                }
            }
        }, id).start());
    }

    @Override
    public void onEvent(ObservableRunnable.RunnableEvent event) {
        synchronized (LOCK) {
            Optional.of("The runnable [" + event.getThread().getName() + "] data changed and state is : [" + event.getState() + "]")
                    .ifPresent(System.out::println);
            if (event.getThrowable() != null) {
                Optional.of("The runnable [" + event.getThread().getName() + "] process failed")
                        .ifPresent(System.out::println);
                event.getThrowable().getCause().printStackTrace();
            }
        }
    }

}
