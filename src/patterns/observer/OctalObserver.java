package patterns.observer;

/**
 * @Author lishaohui
 * @Date 2023/4/17 22:08
 */
public class OctalObserver extends Observer {

    public OctalObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println(this.getClass().getSimpleName() + "   Octal String " + Integer.toOctalString(subject.getState()));
    }
}
