package aop.predicate;

/**
 * @Author lishaohui
 * @Date 2023/5/26 18:24
 */
public class TargetFilterDemo {

    public static void main(String[] args) throws ClassNotFoundException {

        String targetClassName = "aop.predicate.A";
        // 获取当前线程的classloader
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // 获取目标类
        Class<?> targetClass = classLoader.loadClass(targetClassName);
        // 方法定义

    }


}
