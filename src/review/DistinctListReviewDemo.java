package review;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author lishaohui
 * @Date 2023/4/28 20:17
 */
public class DistinctListReviewDemo {

    /*
    实现List集合去重的实现思路
    1. 自定义去重方式：通过循环遍历集合判断某个元素是否存在多个，如果存在则删除此重复项，直至集合不存在重复元素为止
    2. 通过Set集合去重。利用Set集合自身的特性实现List的去重
    3. 使用Java8中Steam流中的去重功能
     */
    public static void main(String[] args) {

        List<Person> list = new ArrayList<>();
        list.add(new Person("李四", "123456", 20));
        list.add(new Person("张三", "123456", 18));
        list.add(new Person("王五", "123456", 22));
        list.add(new Person("张三", "123456", 18));
        distinctList4(list);
    }

    // 方式一：自定义去重方式
    /*
        自定义去重方式一：创建一个新集合，通过循环原集合判断循环元素是否存在于新集合中
        如果不存在就插入，否则忽略。 这样循环完毕之后，新集合就是一个没有重复元素的集合
    */
    private static <T> void distinctList1(List<T> personList) {
        List<T> newList = new ArrayList<>(personList.size());
        personList.forEach(person -> {
            // 如果新集合中不存在则插入
            if (!newList.contains(person)) {
                newList.add(person);
            }
        });
        newList.forEach(System.out::println);
    }

    /*
    自定义去重方式二：
      使用迭代器循环并判断当前元素首次出现的位置（indexOf）是否等于最后出现的位置（lastIndexOf）
      如果不等于则说明此元素为重复元素，删除当前元素即可。
      循环完毕就能得到一个没有重复元素的集合
     */
    private static <T> void distinctList2(List<T> list) {
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (list.indexOf(current) != list.lastIndexOf(current)) {
                iterator.remove();
            }
        }
        list.forEach(System.out::println);
    }

    private static <T> void distinctList3(List<T> list) {
        LinkedHashSet<T> set = new LinkedHashSet<>(list);
        set.forEach(System.out::println);
    }

    private static <T> void distinctList4(List<T> list) {
        list
                .stream()
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }


}

