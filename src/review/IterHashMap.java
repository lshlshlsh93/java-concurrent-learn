package review;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author lishaohui
 * @Date 2023/4/28 19:36
 */
public class IterHashMap {

    /*
        HashMap有几种遍历方式，推荐使用哪种方式
        第一种方式：使用entrySet循环遍历方式
        第二种方式：使用keySet循环遍历方式
        第三种方式：使用entrySet迭代器方式
        第四种方式：使用keySet迭代器方式
     */


    public static void main(String[] args) {

        // Map的几种遍历方式
        HashMap<String, String> map = new HashMap<>() {{
            put("Tom", "sleep");
            put("Lucy", "Study");
            put("Jack", "Eat");
        }};
        // 第一种方式,通过EntrySet循环方式
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        // 第二种方式：通过KeySet循环方式
        // KeySet循环不建议使用，因为循环了两次，效率比较低。
        for (String key : map.keySet()) {
            System.out.println(key + ":" + map.get(key));
        }
        // 第三种方式：通过EntrySet迭代器方式
        Iterator<Map.Entry<String, String>> entrySetIterator = map.entrySet().iterator();
        while (entrySetIterator.hasNext()) {
            Map.Entry<String, String> entry = entrySetIterator.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        // 第四种方式:通过KeySet迭代器方式
        Iterator<String> keySetIterator = map.keySet().iterator();
        while (keySetIterator.hasNext()) {
            String key = keySetIterator.next();
            System.out.println(key + ":" + map.get(key));
        }
        // 使用迭代器的优点是可以在循环的时候，动态的删除集合中的元素
        // 不使用迭代器方式时不能在循环时动态删除集合中的元素，会发生报错


        // 第五种方式：使用Java8新语法lambda表达式foreach方式
        map.forEach((k, v) -> System.out.println(k + ":" + v));

        // 第六种方式：使用Stream流单线程遍历
        map.entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + ":" + entry.getValue()));

        // 第七种方式：使用Stream流多线程遍历,执行了一个parallel并发执行的方法
        // parallel方法会根据当前硬件配置生成对应的线程数
        // 遍历顺序可能不同，因为程序是并发执行的
        map.entrySet().stream().parallel().forEach(entry -> System.out.println(entry.getKey() + ":" + entry.getValue()));

    }


}
