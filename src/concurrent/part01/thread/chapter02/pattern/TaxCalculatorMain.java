package concurrent.part01.thread.chapter02.pattern;

/**
 * @Author lishaohui
 * @Date 2023/3/19 0:16
 */
public class TaxCalculatorMain {
    //这是main方法,程序的入口
    public static void main(String[] args) {

//        TaxCalculator taxCalculator = new TaxCalculator(10000d, 2000d) {
//            @Override
//            protected double calcTax() {
//                return getSalary() * 0.1 + getBonus() * 0.15;
//            }
//        };
//        double tax = taxCalculator.calculate();
//        System.out.println(tax);

        double salary = 10000d;
        double bonus = 2000d;
        TaxCalculator taxCalculator = new TaxCalculator(salary, bonus, (s, b) -> s * 0.1 + b * 0.15);
        System.out.println(taxCalculator.calculate());
    }
}
