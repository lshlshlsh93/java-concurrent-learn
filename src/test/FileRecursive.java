package test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author lishaohui
 * @Date 2023/6/2 18:28
 */
public class FileRecursive {

    private static final String DIRECTORY_PATH = "D:\\Java项目\\Test\\src\\concurrent";

    public static void main(String[] args) {
        List<File> fileList = new ArrayList<>();
        File file = new File(DIRECTORY_PATH);
        getAllFilePath(file, fileList);
        fileList.forEach(System.out::println);
    }

    public static void getAllFilePath(File file, List<File> fileList) {
        if (file.isHidden()) {
            return;
        }
        if (file.isFile()) {
            fileList.add(file);
        } else {
            File[] files = file.listFiles();
            Arrays.stream(files).forEach(f -> getAllFilePath(f, fileList));
        }
    }

}
