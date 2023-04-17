package patterns.command;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lishaohui
 * @Date 2023/4/12 22:47
 */
public class CommandPattern {

    private interface Order {
        void execute();
    }

    public static class Stock {

        private final String name = "ABC";
        private final int quantity = 10;

        public void buy() {
            System.out.println("Stock [ Name: " + name + ", Quantity: " + quantity + " ] bought");
        }

        public void sell() {
            System.out.println("Stock [ Name: " + name + ", Quantity: " + quantity + " ] sold");
        }
    }

    public static class BuyStock implements Order {
        private final Stock abcStock;

        public BuyStock(Stock abcStock) {
            this.abcStock = abcStock;
        }

        public void execute() {
            abcStock.buy();
        }
    }

    public static class SellStock implements Order {
        private final Stock abcStock;

        public SellStock(Stock abcStock) {
            this.abcStock = abcStock;
        }

        public void execute() {
            abcStock.sell();
        }
    }

    public static class Broker {
        private final List<Order> orderList = new ArrayList<>();

        public void takeOrder(Order order) {
            orderList.add(order);
        }

        public void placeOrders() {

            for (Order order : orderList) {
                order.execute();
            }
            orderList.clear();
        }
    }

    public static void main(String[] args) {

        Stock stock = new Stock();

        BuyStock buyStockOrder = new BuyStock(stock);
        SellStock sellStockOrder = new SellStock(stock);

        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);

        broker.placeOrders();
    }


}
