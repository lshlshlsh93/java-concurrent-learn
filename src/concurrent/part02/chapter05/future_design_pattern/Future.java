package concurrent.part02.chapter05.future_design_pattern;

/**
 * @Author lishaohui
 * @Date 2023/4/24 0:05
 */
public interface Future<T> {

    T get() throws InterruptedException;

}
