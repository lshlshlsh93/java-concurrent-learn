package concurrent.part02.chapter14.test;

import concurrent.part02.chapter14.active_object_design_pattern.ActiveObject;
import concurrent.part02.chapter14.active_object_design_pattern.ActiveObjectFactory;
import concurrent.part02.chapter14.active_object_design_pattern.DisplayClientThread;
import concurrent.part02.chapter14.active_object_design_pattern.MakerClientThread;

/**
 * @Author lishaohui
 * @Date 2023/5/16 12:54
 */
public class ActiveObjectClientTest {

    public static void main(String[] args) {

        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        new MakerClientThread(activeObject, "Lsh").start();
        new MakerClientThread(activeObject, "John").start();

        new DisplayClientThread("Tom", activeObject).start();

    }

}
