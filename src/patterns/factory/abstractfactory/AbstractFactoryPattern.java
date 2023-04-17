package patterns.factory.abstractfactory;

import java.util.Optional;

/**
 * <p>
 * 抽象工厂模式
 * </p>
 *
 * @Author lishaohui
 * @Date 2023/4/13 23:51
 */
public class AbstractFactoryPattern {


    interface Obstacle {
        void action();
    }

    interface Player {
        void interactWith(Obstacle obstacle);
    }

    static class Kitty implements Player {
        @Override
        public void interactWith(Obstacle obstacle) {
            Optional.of("kitty has encountered a ").ifPresent(System.out::println);
            obstacle.action();
        }
    }

}
