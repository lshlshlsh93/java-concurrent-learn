package concurrent.part02.chapter01.singleton.lazy;

/**
 * @Author lishaohui
 * @Date 2023/4/12 23:51
 */
public class LazySingletonOne {

    private static LazySingletonOne instance;

    private LazySingletonOne() {
    }

    public static LazySingletonOne getInstance() {
        if (null == instance) {
            instance = new LazySingletonOne();
        }
        return LazySingletonOne.instance;
    }

}
