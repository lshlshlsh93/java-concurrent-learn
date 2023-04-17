package concurrent.part01.thread.chapter11.test;

import java.util.Arrays;
import java.util.Optional;

/**
 * @Author lishaohui
 * @Date 2023/4/11 15:56
 */
public class Test {

    private static class Test1 {

        Test2 test2 = new Test2();

        public void test() {
            test2.test();
        }
    }

    private static class Test2 {
        public void test() {
            Arrays
                    .asList(Thread.currentThread().getStackTrace())
                    .stream()
                    .filter(se -> !se.isNativeMethod())
                    .forEach(se ->
                            Optional
                                    .of(se.getClassName() + ":" + se.getMethodName() + ":" + se.getLineNumber())
                                    .ifPresent(System.out::println)
                    );
        }
    }


    public static void main(String[] args) {
        new Test1().test();
    }

}
