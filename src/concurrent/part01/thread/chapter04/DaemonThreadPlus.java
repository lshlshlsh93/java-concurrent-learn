package concurrent.part01.thread.chapter04;

/**
 * @Author lishaohui
 * @Date 2023/3/19 13:20
 */

public class DaemonThreadPlus {


    /**
     * <p>
     * 一个实际要使用daemon线程的场景：客户端与服务器端建立一个TCP的长链接，
     * </p>
     * 然后当连接建立之后就创建一个线程来给服务器发送心跳包以便服务器能监听客户端的网络状态，
     * <p>
     * 这时如果连接断开了那这个心跳线程也得跟着断开
     * </p>
     * <p>
     * A<------------------------------------------->B
     * </p>
     * <p>
     * ->daemonThread(health check)
     * </p>
     */
    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            Thread innerThread = new Thread(() -> {
                try {
                    while (true){
                        System.out.println(" do something for health check");
                        Thread.sleep(1_000L);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            // 将innerThread设置为守护线程
            innerThread.setDaemon(true);
            innerThread.start();
            try {
                Thread.sleep(1_000L);
                System.out.println("T thread has finish done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
    }

}
