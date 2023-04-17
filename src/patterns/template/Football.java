package patterns.template;

import java.util.Optional;

/**
 * @Author lishaohui
 * @Date 2023/4/12 22:20
 */
public class Football extends Game {
    @Override
    void initialize() {
        Optional.of("Football Game Initialized! Start playing.").ifPresent(System.out::println);
    }

    @Override
    void startPlay() {
        Optional.of("Football Game Started. Enjoy the game!").ifPresent(System.out::println);
    }

    @Override
    void endPlay() {
        Optional.of("Football Game Finished.").ifPresent(System.out::println);
    }
}
