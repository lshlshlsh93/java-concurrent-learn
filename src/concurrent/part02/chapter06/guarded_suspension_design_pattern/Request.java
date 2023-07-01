package concurrent.part02.chapter06.guarded_suspension_design_pattern;

/**
 * @Author lishaohui
 * @Date 2023/4/24 0:44
 */
public class Request {

    private final String value;

    public Request(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
