package concurrent.part02.chapter11;

/**
 * @Author lishaohui
 * @Date 2023/4/24 11:23
 */
public class Message {

    private final String value;

    public Message(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
