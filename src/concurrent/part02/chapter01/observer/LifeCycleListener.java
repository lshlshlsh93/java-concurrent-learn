package concurrent.part02.chapter01.observer;

/**
 * @Author lishaohui
 * @Date 2023/4/17 22:28
 */
public interface LifeCycleListener {

    void onEvent(ObservableRunnable.RunnableEvent runnableEvent);


}
