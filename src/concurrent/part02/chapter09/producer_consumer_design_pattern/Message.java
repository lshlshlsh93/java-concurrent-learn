package concurrent.part02.chapter09.producer_consumer_design_pattern;

/**
 * @Author lishaohui
 * @Date 2023/4/24 10:45
 */
public class Message {

    private final String data;

    public Message(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

}
