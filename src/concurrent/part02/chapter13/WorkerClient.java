package concurrent.part02.chapter13;

/**
 * @Author lishaohui
 * @Date 2023/4/24 22:34
 */
public class WorkerClient {

    public static void main(String[] args) {

        final Channel channel = new Channel(5);
        channel.startWork();

        new Transport("lsh", channel).start();
        new Transport("tom", channel).start();
        new Transport("lucy", channel).start();
    }

}
