package concurrent.part01.thread.chapter02.example;

/**
 * @Author lishaohui
 * @Date 2023/3/18 23:51
 */
public class TicketWindow extends Thread{

    public static final int MAX = 50;

    private final  String name;

    private static int index= 1;

    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (index<=MAX){
            System.out.println("柜台名："+name+" 当前的号码是："+index++);
        }
    }
}
