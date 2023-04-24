package concurrent.part01.thread.test;

/**
 * @Author lishaohui
 * @Date 2023/3/22 15:53
 */
public class SingleDoubleCheck {

    private static SingleDoubleCheck instance = null;

    // 构造函数私有化
    private SingleDoubleCheck() {
    }

    public static SingleDoubleCheck getInstance() {
        if (instance == null) {
            synchronized (SingleDoubleCheck.class) {
                if (instance == null) {
                    instance = new SingleDoubleCheck();
                }
            }
        }
        return instance;
    }
}

class SingleDoubleCheckWithVolatile {
    private static volatile SingleDoubleCheckWithVolatile instance = null;

    private SingleDoubleCheckWithVolatile() {
    }

    public static SingleDoubleCheckWithVolatile getInstance() {
        if (null == instance) {
            synchronized (SingleDoubleCheckWithVolatile.class) {
                if (null == instance) {
                    instance = new SingleDoubleCheckWithVolatile();
                }
            }
        }
        return instance;
    }
}


class SingletonWithHolder {

    static class ContextHolder {
        private static final SingletonWithHolder instance = new SingletonWithHolder();
    }

    public static SingletonWithHolder getSingletonWithHolder() {
        return ContextHolder.instance;
    }

}


