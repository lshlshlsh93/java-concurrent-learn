package review;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @Author lishaohui
 * @Date 2023/4/28 20:03
 */
public class ComparatorReviewDemo {

    public static void main(String[] args) {
        // 构建list集合数据
        List<Person> personList =
                Arrays.asList(
                        new Person(1, "jack", 18),
                        new Person(2, "lucy", 20),
                        new Person(3, "tom", 15)
                );
        // 根据person类中compareTo方法定义的规则进行排序操作
        // personList.sort(personList, (p1, p2) -> p2.getAge() - p1.getAge());// lambda方式
        personList.sort(new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                // 升序排序
                return p1.getAge() - p2.getAge();
            }
        });
        // 遍历输出结果
        personList.forEach(p -> Optional.of(p.getName() + ":" + p.getAge()).ifPresent(System.out::println));
    }

    private static class Person {
        private int id;
        private String name;
        private int age;
        public Person(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
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
    }

}
