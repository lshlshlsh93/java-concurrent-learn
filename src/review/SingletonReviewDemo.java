package review;

/**
 * <div>
 * 实现单例模式有四种方法
 * 方式一：饿汉式
 * 方式二：懒汉式：双重校验锁+volatile实现
 * 方式三：内部类方式：Holder方式
 * 方式四：枚举方式
 * </div>
 *
 * @Author lishaohui
 * @Date 2023/4/28 21:03
 */
public class SingletonReviewDemo {

    /**
     * <div>
     * 饿汉式单例模式
     * 在类加载时就创建并初始化实例对象，不存在线程安全问题。
     * 优点：实现简单，不存在线程安全问题
     * 缺点：类加载时就创建实例对象，如果该对象没有被使用，就会造成资源浪费
     * </div>
     */
    static class HungrySingleTon {
        private static final HungrySingleTon instance = new HungrySingleTon();

        private HungrySingleTon() {
        }

        public HungrySingleTon getInstance() {
            return instance;
        }
    }


    /**
     * <div>
     *     懒汉模式：
     *     使用双重校验锁和volatile来保证线程安全。
     *     只有在第一次使用时，才会被初始化。
     * </div>
     */
    static class LazySingleTon {
        // 利用volatile可以实现可见性和（禁止重排序）有序性，不保证原子性
        // 使用volatile关键字解决可能出现空指针问题
        private static volatile LazySingleTon instance = null;
        // 构造函数私有化
        private LazySingleTon() {
        }
        // 获取实例的公共方法
        public LazySingleTon getInstance() {
            // 第一次校验
            if (instance == null) {
                synchronized (LazySingleTon.class) {
                    // 第二次校验
                    if (instance == null) {
                        instance = new LazySingleTon();
                    }
                }
            }
            return instance;
        }
    }

    // 通过内部类方式
    static class InnerClassSingleton {
        private InnerClassSingleton() {
        }

        private static class SingletonHolder {
            private static final InnerClassSingleton INSTANCE = new InnerClassSingleton();
        }

        public static InnerClassSingleton getInstance() {
            return SingletonHolder.INSTANCE;
        }
    }

    // 通过枚举方式实现
    static class EnumMethodSingleton {
        public enum EnumSingleton {
            INSTANCE;

            public EnumSingleton getInstance() {
                return INSTANCE;
            }
        }
    }


}
