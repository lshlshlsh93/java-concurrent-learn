package concurrent.part02.chapter08.code;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @Author lishaohui
 * @Date 2023/4/24 2:17
 */
public class BalkingData {

    private final String filename;

    private String content;

    private boolean changed;


    public BalkingData(String filename, String content) {
        this.filename = filename;
        this.content = content;
        this.changed = true;
    }

    public synchronized void change(String newContent) {
        this.content = newContent;
        this.changed = true;
    }

    public synchronized void save() throws IOException {
        if (!changed) {
            return;
        }
        doSave();
        this.changed = false;
    }

    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName() + "calls do save");
        try (Writer writer = new FileWriter(filename, true)) {
            writer.write(content);
            writer.write("\n");
            writer.flush();
        }
    }

}
