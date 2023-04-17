package concurrent.part02.chapter01.singleton.graceful;

/**
 * @Author lishaohui
 * @Date 2023/4/13 0:15
 */
public class GracefulSingletonTwo {

    private GracefulSingletonTwo() {
    }

    private static final class InstanceHolder {
        private static final GracefulSingletonTwo instance = new GracefulSingletonTwo();
    }

    public static GracefulSingletonTwo getInstance() {
        return InstanceHolder.instance;
    }

}
