package concurrent.part02.chapter01.single_thread_execution;

/**
 * @Author lishaohui
 * @Date 2023/4/17 22:49
 * <p>
 * 共享资源
 * </p>
 */
public class Gate {

    private int counter = 0;
    private String name = "nobody";
    private String address = "nowhere";

    /**
     * 临界值
     *
     * @param name    姓名
     * @param address 地址
     */
    public synchronized void pass(String name, String address) {
        this.counter++;
        // race
        this.name = name;
        this.address = address;
        verify();
    }

    private void verify() {
        if (this.name.charAt(0) != this.address.charAt(0)) {
            System.out.println("*****BROKEN*******" + toString());
        }
    }

    @Override
    public synchronized String toString() {
        return "No." + counter + ":" + name + "," + address;
    }
}
