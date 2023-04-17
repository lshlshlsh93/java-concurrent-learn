package concurrent.part01.thread.pattern;

/**
 * @Author lishaohui
 * @Date 2023/3/17 23:53
 */
public class QueryFromHttpAction {

    public void execute() {
        Context context = ActionContext.getActionContext().getContext();
        String name = context.getName();
        String cardId = getCardId(name);
        context.setCardId(cardId);
    }

    private String getCardId(String name) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "4104811965" + Thread.currentThread().getId();
    }


}
