package test;

/**
 * @Author lishaohui
 * @Date 2023/6/2 21:42
 */
public class Main {

    public static void main(String[] args) {
        String str1 = new String("hhh"); // new时会开辟新的地址
        String str2 = "hhh";
        String str3 = "hhh";
        System.out.println(str1==str2); // false
        System.out.println(str2==str3); // true
    }

}
