package concurrent.part02.chapter07.thread_local;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author lishaohui
 * @Date 2023/4/24 1:28
 */
public class ThreadLocalSimulator<T> {

    private final Map<Thread, T> THREAD_STORAGE_MAP = new HashMap<>();

    public void set(T t) {
        synchronized (this) {
            Thread key = Thread.currentThread();
            THREAD_STORAGE_MAP.put(key, t);
        }
    }

    public T get() {
        synchronized (this) {
            Thread key = Thread.currentThread();
            T val = THREAD_STORAGE_MAP.get(key);
            if (val == null) {
                return initialValue();
            }
            return val;
        }
    }

    public T initialValue() {
        return null;
    }


}
