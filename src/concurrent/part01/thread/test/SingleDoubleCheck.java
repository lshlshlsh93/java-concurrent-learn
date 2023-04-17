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
