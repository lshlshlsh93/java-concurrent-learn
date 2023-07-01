package test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * @Author lishaohui
 * @Date 2023/6/2 18:03
 */
public class TryWithResource {


    private static final String FILE_PATH = "D:\\Java项目\\Test\\src\\test\\test.txt";

    private static final String COPY_FILE_PATH = "D:\\Java项目\\Test\\src\\test\\copy.txt";

    public static void testFileCopy() {
        try (
                FileInputStream fis = new FileInputStream(FILE_PATH);
                BufferedInputStream bis = new BufferedInputStream(fis);
                FileOutputStream fos = new FileOutputStream(COPY_FILE_PATH);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
        ) {
            int size;
            byte[] buf = new byte[1024];
            while ((size = bis.read(buf)) != -1) {
                bos.write(buf, 0, size);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testFileCopy();
    }

}
