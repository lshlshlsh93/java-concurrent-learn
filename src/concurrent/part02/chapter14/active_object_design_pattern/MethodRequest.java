package concurrent.part02.chapter14.active_object_design_pattern;

/**
 * @Author lishaohui
 * @Date 2023/5/16 11:37
 * <div>
 *     对应ActiveObject的每一个方法
 * </div>
 */
public abstract class MethodRequest {

    protected final Servant servant;

    protected final FutureResult futureResult;

    public MethodRequest(Servant servant, FutureResult futureResult) {
        this.servant = servant;
        this.futureResult = futureResult;
    }

    public abstract void execute();


}
