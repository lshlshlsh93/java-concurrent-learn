package concurrent.part02.chapter01.single_thread_execution;

/**
 * @Author lishaohui
 * @Date 2023/4/17 22:56
 */
public class Client {

    public static void main(String[] args) {
        Gate gate = new Gate();
        User hhh = new User("Bsss", "Beijing", gate);
        User sss = new User("Sssss", "Shenzhen", gate);
        User aaa = new User("Gaaa", "Guangzhou", gate);
        hhh.start();
        sss.start();
        aaa.start();
    }

}
