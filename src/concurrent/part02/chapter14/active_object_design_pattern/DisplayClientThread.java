package concurrent.part02.chapter14.active_object_design_pattern;

/**
 * @Author lishaohui
 * @Date 2023/5/16 12:46
 */
public class DisplayClientThread extends Thread {

    private final ActiveObject activeObject;

    public DisplayClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                String text = Thread.currentThread().getName() + "=>" + i;
                activeObject.displayString(text);
                Thread.sleep(200L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
