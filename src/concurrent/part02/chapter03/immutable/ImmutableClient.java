package concurrent.part02.chapter03.immutable;

import java.util.stream.IntStream;

/**
 * @Author lishaohui
 * @Date 2023/4/23 23:15
 */
public class ImmutableClient {

    public static void main(String[] args) {
        Person person = new Person("aaa","aaa");
        IntStream.range(0,5).forEach(i-> new User(person).start());
    }

}
