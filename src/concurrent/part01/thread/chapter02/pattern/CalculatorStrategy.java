package concurrent.part01.thread.chapter02.pattern;

/**
 * @Author lishaohui
 * @Date 2023/3/19 0:22
 */
@FunctionalInterface
public interface CalculatorStrategy {

    double calculate(double salary,double bonus);

}
