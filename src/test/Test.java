package test;


import java.util.stream.IntStream;

/**
 * @Author lishaohui
 * @Date 2023/4/11 10:24
 */
public class Test {

    //这是main方法,程序的入口
    public static void main(String[] args) {

        byte bt = 's'; // 1B
        int a = 100; // 4B
        short sh = 10; // 2B
        long l = 10_000L; // 8B
        boolean flag = false;
        char ch = 'l'; // 2B
        float f = 0.0f; // 4B
        double d = 0.0d; // 8B
        


//        IntStream.rangeClosed(1,100).forEach(System.out::println);


    }

    public void print(String value) {
    }

    public void print(String value, int size) {
    }

    private long print(long value) {
        return value;
    }
    private void print(String v1,String v2) throws Exception {
        Thread.sleep(1_000L);
    }
    private void print() throws InterruptedException {
        Thread.sleep(1_000L);
    }

}
