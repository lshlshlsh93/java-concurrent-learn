package test;

import java.util.HashMap;

/**
 * @Author lishaohui
 * @Date 2023/6/2 16:54
 */
public class BitCalculate {

    public static void main(String[] args) {
        System.out.println(2 * 8);
        // 左移n位，相当于乘以2的n次方
        System.out.println(2 << 3); // 2*2^3=16
        System.out.println(5 << 3); // 5*2^3=40
        swap2(10, 21);
    }

    public static void swap(int a, int b) {
        System.out.printf("a=%d,b=%d\n", a, b);
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.printf("a=%d,b=%d\n", a, b);
    }

    public static void swap2(int a, int b) {
        System.out.printf("a=%d,b=%d\n", a, b);
        a = a ^ b; // a1=a^b
        b = b ^ a; // b=b^a^b -->b=a
        a = a ^ b; // a=a1^b=a^b^a -->a=b
        System.out.printf("a=%d,b=%d\n", a, b);
    }
}
