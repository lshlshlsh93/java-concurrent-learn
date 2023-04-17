package concurrent.part01.thread.chapter02.example;

/**
 * @Author lishaohui
 * @Date 2023/3/18 23:37
 */
public class Bank {


    /**
     * 多线程方式模拟银行挂号
     */
    //这是main方法,程序的入口
    public static void main(String[] args) {
        TicketWindow ticketWindow1 = new TicketWindow("one");
        ticketWindow1.start();
        TicketWindow ticketWindow2 = new TicketWindow("two");
        ticketWindow2.start();
        TicketWindow ticketWindow3 = new TicketWindow("three");
        ticketWindow3.start();
    }

}
