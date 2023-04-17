package concurrent.part02.chapter01.singleton.graceful;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @Author lishaohui
 * @Date 2023/4/13 0:20
 */
public class GracefulSingletonThree {

    private GracefulSingletonThree() {
    }

    private enum Singleton {
        INSTANCE;
        private final GracefulSingletonThree instance;

        Singleton() {
            instance = new GracefulSingletonThree();
        }

        public GracefulSingletonThree getInstance() {
            return instance;
        }
    }

    public static GracefulSingletonThree getInstance() {
        return Singleton.INSTANCE.getInstance();
    }


    public static void main(String[] args) {
        IntStream
                .rangeClosed(0, 100)
                .forEach(
                        i -> new Thread(String.valueOf(i)) {
                            @Override
                            public void run() {
                                Optional.of(GracefulSingletonThree.getInstance()).ifPresent(System.out::println);
                            }
                        }.start()
                );
    }


}
