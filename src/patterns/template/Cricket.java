package patterns.template;

import java.util.Optional;

/**
 * @Author lishaohui
 * @Date 2023/4/12 22:18
 */
public class Cricket extends Game{
    @Override
    void initialize() {
        Optional.of("Cricket Game Initialized! Start playing.").ifPresent(System.out::println);
    }

    @Override
    void startPlay() {
        Optional.of("Cricket Game Started. Enjoy the game!").ifPresent(System.out::println);
    }

    @Override
    void endPlay() {
        Optional.of("Cricket Game Finished.").ifPresent(System.out::println);
    }
}
