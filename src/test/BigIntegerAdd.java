package test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author lishaohui
 * @Date 2023/5/13 22:28
 * <div>
 *     高精度加法
 * </div>
 */
public class BigIntegerAdd {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.next(), str2 = scanner.next();
        List<Integer> A = new ArrayList<>(), B = new ArrayList<>();
        for (int i = str1.length() - 1; i >= 0; i--) A.add(str1.charAt(i) - '0');
        for (int i = str2.length() - 1; i >= 0; i--) B.add(str2.charAt(i) - '0');
        List<Integer> result = add(A, B);
        for (int i = result.size() - 1; i >= 0; i--) System.out.print(result.get(i));
        scanner.close();
    }

    private static List<Integer> add(List<Integer> A, List<Integer> B) {
        if (A.size() < B.size()) return add(B, A);
        int next = 0;
        List<Integer> C = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            next += A.get(i);
            if (i < B.size()) next += B.get(i);
            C.add(next % 10);
            next /= 10;
        }
        if (next != 0) C.add(next);
        return C;
    }

}
