package patterns.observer;

import java.util.Optional;

/**
 * @Author lishaohui
 * @Date 2023/4/17 22:10
 */
public class HexObserver extends Observer {

    public HexObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        Optional
                .of(this.getClass().getSimpleName() + " -->  Hex String: "
                        + Integer.toHexString(subject.getState()).toUpperCase())
                .ifPresent(System.out::println);
    }
}
