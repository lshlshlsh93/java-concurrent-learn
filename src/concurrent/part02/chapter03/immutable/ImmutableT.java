package concurrent.part02.chapter03.immutable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author lishaohui
 * @Date 2023/4/23 23:36
 */
public class ImmutableT {

    private final int age;

    private final String name;

    private final List<String> list;

    public ImmutableT(int age, String name) {
        this.age = age;
        this.name = name;
        this.list = new ArrayList<>();
    }

    public int getAge() {
        return age;
    }

    public List<String> getList() {
        return Collections.unmodifiableList(list); // 不可变对象
    }

    public String getName() {
        return name;
    }
}
