package concurrent.part01.thread.chapter02.pattern;

/**
 * @Author lishaohui
 * @Date 2023/3/19 0:14
 */
public class TaxCalculator {

    private final double salary;

    private final double bonus;

    private final CalculatorStrategy calculatorStrategy;

    public TaxCalculator(double salary, double bonus, CalculatorStrategy calculatorStrategy) {
        this.salary = salary;
        this.bonus = bonus;
        this.calculatorStrategy = calculatorStrategy;
    }

    protected double calcTax(){
        return calculatorStrategy.calculate(salary,bonus);
    }

    public double calculate(){
        return this.calcTax();
    }

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }

}
