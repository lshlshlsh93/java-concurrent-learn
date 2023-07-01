package concurrent.part02.chapter14.active_object_design_pattern;

/**
 * @Author lishaohui
 * @Date 2023/5/16 12:50
 */
public class MakerClientThread extends Thread {

    private final ActiveObject activeObject;

    private final char fillChar;

    public MakerClientThread(ActiveObject activeObject, String name) {
        super(name);
        this.activeObject = activeObject;
        this.fillChar = name.charAt(0);
    }


    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                Result result = activeObject.makeString(i + 1, fillChar);
                Thread.sleep(20L);
                String value = (String) result.getDefaultValue();
                System.out.println(Thread.currentThread().getName() + "-->value: " + value);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
