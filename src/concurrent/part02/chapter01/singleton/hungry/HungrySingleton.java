package concurrent.part02.chapter01.singleton.hungry;

/**
 * @Author lishaohui
 * @Date 2023/4/12 23:47
 * <p>
 * 饿汉式单例模式
 * </p>
 */
public class HungrySingleton {

    /**
     * 存在的问题：不能懒加载
     */
    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return instance;
    }


}
