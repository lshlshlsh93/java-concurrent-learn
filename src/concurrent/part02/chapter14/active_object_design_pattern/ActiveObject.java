package concurrent.part02.chapter14.active_object_design_pattern;

/**
 * @Author lishaohui
 * @Date 2023/5/16 11:33
 * <div>
 *     接收异步消息的主动方法
 * </div>
 */

public interface ActiveObject {

    Result makeString(int count, char fillChar);

    void displayString(String text);


}
