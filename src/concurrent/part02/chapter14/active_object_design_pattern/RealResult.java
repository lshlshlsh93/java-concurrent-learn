package concurrent.part02.chapter14.active_object_design_pattern;

/**
 * @Author lishaohui
 * @Date 2023/5/16 12:09
 */
public class RealResult implements Result {

    private final Object resultValue;

    public RealResult(Object resultValue) {
        this.resultValue = resultValue;
    }

    @Override
    public Object getDefaultValue() {
        return this.resultValue;
    }
}
