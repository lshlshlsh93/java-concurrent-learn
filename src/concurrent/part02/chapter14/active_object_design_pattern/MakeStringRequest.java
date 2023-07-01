package concurrent.part02.chapter14.active_object_design_pattern;

/**
 * @Author lishaohui
 * @Date 2023/5/16 12:22
 * <div>
 *   {@link ActiveObject#makeString(int, char)}
 * </div>
 */
public class MakeStringRequest extends MethodRequest {

    private final char fillChar;

    private final int count;

    public MakeStringRequest(Servant servant, FutureResult futureResult, int count, char fillChar) {
        super(servant, futureResult);
        this.fillChar = fillChar;
        this.count = count;
    }

    @Override
    public void execute() {
        Result result = servant.makeString(count, fillChar);
        futureResult.setResult(result);
    }

}
