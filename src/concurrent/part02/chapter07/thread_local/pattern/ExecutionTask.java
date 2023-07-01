package concurrent.part02.chapter07.thread_local.pattern;

/**
 * @Author lishaohui
 * @Date 2023/3/17 23:58
 */
public class ExecutionTask implements Runnable {

    private final QueryFromDatabaseAction queryFromDatabaseAction = new QueryFromDatabaseAction();

    private final QueryFromHttpAction queryFromHttpAction = new QueryFromHttpAction();

    @Override
    public void run() {
        queryFromDatabaseAction.execute();
        // 模拟日志记录
        System.out.println("The name query successful");
        queryFromHttpAction.execute();
        System.out.println("The cardId is query successful");

        Context context = ActionContext.getActionContext().getContext();
        System.out.println("The name is:  " + context.getName() + "   cardId:  " + context.getCardId());
    }
}
