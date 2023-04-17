package patterns.observer;

/**
 * @Author lishaohui
 * @Date 2023/4/17 22:08
 */
public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("   Binary String " + Integer.toBinaryString(subject.getState()));
    }
}
