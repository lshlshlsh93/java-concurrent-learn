package patterns.singleton;

import java.util.Optional;

/**
 * @Author lishaohui
 * @Date 2023/4/12 13:59
 */
public class SingletonPattern {


    // 饿汉式单例类.在类初始化时，已经自行实例化
    private static class HungrySingleton {

        // 实例对象
        private static final HungrySingleton instance = new HungrySingleton();

        // 无参构造函数私有化
        private HungrySingleton() {
        }

        // 静态工厂方法
        public static HungrySingleton getInstance() {
            return instance;
        }

        // 方法
        public void showMessage(String message) {
            Optional.of(message).ifPresent(System.out::println);
        }

    }


    private static class SingleTon2 {
        private static volatile SingleTon2 instance = null;

        private SingleTon2() {
        }

        // 在getInstance方法上加synchronized关键字，保证在并发的情况下，只有一个线程能创建INSTANCE对象的实例。
        // 静态工厂方法
        public static SingleTon2 getInstance() {
            if (instance == null) {
                synchronized (SingleTon2.class) {
                    if (instance == null) {
                        instance = new SingleTon2();
                    }
                }
            }
            return instance;
        }
    }


    private static class SingleTon3 {

        private SingleTon3() {
        }

        private static class SingleTonHolder {
            private static final SingleTon3 instance = new SingleTon3();
        }

        public SingleTon3 getInstance() {
            return SingleTonHolder.instance;
        }


    }


    public static void main(String[] args) {

        HungrySingleton instance = HungrySingleton.getInstance();
        instance.showMessage("this is message");

    }


}
