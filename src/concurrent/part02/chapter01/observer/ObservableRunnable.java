package concurrent.part02.chapter01.observer;

/**
 * @Author lishaohui
 * @Date 2023/4/17 22:26
 */
public abstract class ObservableRunnable implements Runnable {

    final protected LifeCycleListener listener;

    public ObservableRunnable(final LifeCycleListener lifeCycleListener) {
        this.listener = lifeCycleListener;
    }

    protected void notifyChange(RunnableEvent runnableEvent) {
        listener.onEvent(runnableEvent);

    }

    public enum RunnableState {
        RUNNING, ERROR, DONE
    }


    public static class RunnableEvent {

        private final RunnableState state;
        private final Thread thread;
        private final Throwable throwable;

        public RunnableEvent(RunnableState state, Thread thread, Throwable throwable) {
            this.state = state;
            this.thread = thread;
            this.throwable = throwable;
        }

        public RunnableState getState() {
            return state;
        }

        public Thread getThread() {
            return thread;
        }

        public Throwable getThrowable() {
            return throwable;
        }
    }

}
