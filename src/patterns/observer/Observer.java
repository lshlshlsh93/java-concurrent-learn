package patterns.observer;

/**
 * @Author lishaohui
 * @Date 2023/4/17 22:05
 */
public abstract class Observer {

    protected Subject subject;

    public Observer(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    public abstract void update();

}
