package test;

import java.util.Optional;

/**
 * @Author lishaohui
 * @Date 2023/4/14 0:06
 */
public class EnumTest {

    private enum T {
        STUDY("study action"),
        SLEEP("sleep action"),
        TALK("talk action"),
        EXERCISE("exercise action");

        T(String description) {

        }

        public static void doStudy() {
            Optional.of(STUDY).ifPresent(System.out::println);
        }

        public static void doSleep() {
            Optional.of(SLEEP).ifPresent(System.out::println);
        }

        public static void doTalk() {
            Optional.of(TALK).ifPresent(System.out::println);
        }

        public static void doExercise() {
            Optional.of(EXERCISE).ifPresent(System.out::println);
        }

        //这是main方法,程序的入口
        public static void main(String[] args) {
            T.doStudy();
            T.doSleep();
            T.doTalk();
            T.doExercise();
        }
    }


}
