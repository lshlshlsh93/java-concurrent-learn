package concurrent.part02.chapter01.singleton.graceful;

/**
 * @Author lishaohui
 * @Date 2023/4/13 0:06
 */
public class GracefulSingletonOne {

    // 使用volatile关键字解决可能出现空指针问题
    // volatile关键字可以保证可见性和（禁止重排序）有序性，不保证原子性
    private static volatile GracefulSingletonOne instance = null;

    private GracefulSingletonOne() {
    }

    // Double-checked locking 双重检查锁
    public static GracefulSingletonOne getInstance() {
        if (null == instance) {
            synchronized (GracefulSingletonOne.class) {
                if (null == instance) {
                    instance = new GracefulSingletonOne();
                }
            }
        }
        return instance;
    }

}
