package concurrent.part02.chapter14.active_object_design_pattern;

/**
 * @Author lishaohui
 * @Date 2023/5/16 12:35
 */
public class SchedulerThread extends Thread {

    private final ActivationQueue activationQueue;

    public SchedulerThread(ActivationQueue activationQueue) {
        this.activationQueue = activationQueue;
    }

    public void invoke(MethodRequest methodRequest) {
        this.activationQueue.put(methodRequest);
    }


    @Override
    public void run() {
        while (true) {
            activationQueue.take().execute();
        }
    }
}
