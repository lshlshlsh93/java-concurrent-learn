package concurrent.part02.chapter01.singleton.lazy;

/**
 * @Author lishaohui
 * @Date 2023/4/12 23:58
 */
public class LazySingletonTwo {

    private static LazySingletonTwo instance;


    private LazySingletonTwo() {
    }

    public synchronized static LazySingletonTwo getInstance() {
        if (null == instance) {
            instance = new LazySingletonTwo();
        }
        return instance;
    }

}
