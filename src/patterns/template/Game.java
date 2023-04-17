package patterns.template;

/**
 * @Author lishaohui
 * @Date 2023/4/12 22:16
 */
public abstract class Game {

    abstract void initialize();

    abstract void startPlay();

    abstract void endPlay();

    /**
     * 模板方法
     */
    public final void play() {

        // 初始化
        initialize();
        // 开始游戏
        startPlay();
        // 结束游戏
        endPlay();

    }

}
