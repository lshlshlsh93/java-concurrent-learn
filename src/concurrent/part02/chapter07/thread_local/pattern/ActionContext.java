package concurrent.part02.chapter07.thread_local.pattern;

/**
 * @Author lishaohui
 * @Date 2023/3/17 23:59
 */
public final class ActionContext {

    private static final ThreadLocal<Context> CONTEXT_THREAD_LOCAL =
            ThreadLocal.withInitial(Context::new);

    // 单例模式
    private static class ContextHolder {
        private final static ActionContext actionContext = new ActionContext();
    }

    public static ActionContext getActionContext() {
        return ContextHolder.actionContext;
    }

    public Context getContext() {
        return CONTEXT_THREAD_LOCAL.get();
    }

}
