package concurrent.part02.chapter14.active_object_design_pattern;

/**
 * @Author lishaohui
 * @Date 2023/5/16 12:27
 */
public class DisplayStringRequest extends MethodRequest {

    private final String text;

    public DisplayStringRequest(Servant servant, final String text) {
        super(servant, null);
        this.text = text;
    }

    @Override
    public void execute() {
        this.servant.displayString(text);
    }


}
