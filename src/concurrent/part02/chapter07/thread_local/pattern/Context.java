package concurrent.part02.chapter07.thread_local.pattern;

/**
 * @Author lishaohui
 * @Date 2023/3/17 23:52
 */
public class Context {

    private String name;

    private String cardId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
