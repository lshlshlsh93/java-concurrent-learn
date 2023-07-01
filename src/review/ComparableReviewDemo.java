package review;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @Author lishaohui
 * @Date 2023/4/28 19:44
 */
public class ComparableReviewDemo {

    public static void main(String[] args) {

        // 构建list集合数据
        List<Person> personList =
                Arrays.asList(
                        new Person(1, "jack", 18),
                        new Person(2, "lucy", 20),
                        new Person(3, "tom", 15)
                );
        // 根据person类中compareTo方法定义的规则进行排序操作
        Collections.sort(personList);
        // 遍历输出结果
        personList.forEach(p -> Optional.of(p.getName() + ":" + p.getAge()).ifPresent(System.out::println));
    }

    /**
     * <p>
     * Comparable 的使用是在自定义对象的类中实现 Comparable 接口，并重写 compareTo 方法来实现自定义排序规则
     * </p>
     * <p>
     * 正序从小到大的排序规则是：使用当前的对象值减去要对比对象的值
     * </p>
     * <p>
     * 正序从小到大的排序规则是：使用当前的对象值减去要对比对象的值;倒序从大到小的排序规则刚好相反：是用对比对象的值减去当前对象的值
     * </p>
     * <p>
     * 如果自定义对象没有实现 Comparable 接口，则不能使用 Collections.sort 方法进行排序的
     * </p>
     */
    private static class Person implements Comparable<Person> {
        private int id;
        private String name;
        private int age;

        public Person(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Person p) {
            // 倒序排序
            return p.getAge() - this.getAge();
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

    }
}



