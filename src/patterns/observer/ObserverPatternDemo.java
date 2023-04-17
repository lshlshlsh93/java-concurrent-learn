package patterns.observer;

/**
 * @Author lishaohui
 * @Date 2023/4/17 22:11
 */
public class ObserverPatternDemo {

    public static void main(String[] args) {

        final Subject subject = new Subject();

        new HexObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);

    }

}
