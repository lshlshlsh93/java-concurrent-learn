package concurrent.part02.chapter14.active_object_design_pattern;

/**
 * @Author lishaohui
 * @Date 2023/5/16 12:42
 */
public final class ActiveObjectFactory {

    private ActiveObjectFactory() {

    }
    public static ActiveObject createActiveObject() {
        Servant servant = new Servant();
        ActivationQueue activationQueue = new ActivationQueue();
        SchedulerThread schedulerThread = new SchedulerThread(activationQueue);
        ActiveObjectProxy activeObjectProxy = new ActiveObjectProxy(schedulerThread, servant);
        schedulerThread.start();
        return activeObjectProxy;
    }


}
