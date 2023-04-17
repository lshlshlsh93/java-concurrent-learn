package concurrent.part01.thread.pattern;

/**
 * @Author lishaohui
 * @Date 2023/3/17 23:52
 */
public class QueryFromDatabaseAction {

    public void execute() {
        try {
            Thread.sleep(1000L);
            String name = "lsh-" + Thread.currentThread().getName();
            ActionContext.getActionContext().getContext().setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
