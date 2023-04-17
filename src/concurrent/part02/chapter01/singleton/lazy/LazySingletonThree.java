package concurrent.part02.chapter01.singleton.lazy;

/**
 * @Author lishaohui
 * @Date 2023/4/13 0:02
 */
public class LazySingletonThree {

    private static LazySingletonThree instance;

    private LazySingletonThree() {
    }

    // Double-checked locking 双重检查锁
    public static LazySingletonThree getInstance() {
        if (null == instance) {
            synchronized (LazySingletonThree.class) {
                if (null == instance) {
                    instance = new LazySingletonThree();
                }
            }
        }
        return instance;
    }

}
