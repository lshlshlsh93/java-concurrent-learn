package concurrent.part01.thread.chapter02.pattern;

/**
 * @Author lishaohui
 * @Date 2023/3/19 0:25
 */
public class SimpleCalculatorStrategy implements CalculatorStrategy{

    private static final double SALARY_RATE = 0.1;

    private static final double BONUS_RATE = 0.15;

    @Override
    public double calculate(double salary, double bonus) {
        return salary * SALARY_RATE + bonus * BONUS_RATE;
    }

}
