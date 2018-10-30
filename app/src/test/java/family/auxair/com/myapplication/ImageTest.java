package family.auxair.com.myapplication;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ImageTest {
    @Test
    public void addition_isCorrect() {
        File root = new File("./img");
        covertFold(root);
    }

    private void covertFold(File file) {

        ;
        System.out.println(file.getAbsolutePath());
        File[] files = file.listFiles();
        for (File src : files) {
            if (src.isFile()) {
                String name = src.getName();
                if (name.contains("@3x")) {
                    src.delete();
                } else {
                    String replace = name.replace("@", "_");
                    replace = replace.replace(" ", "");
                    replace = replace.replace(" ", "");
                    replace = replace.replace(" ", "");
                    replace = replace.replace(" ", "");
                    replace = replace.replace(" ", "");
                    replace = replace.replace(" ", "");
                    replace = replace.replace(" ", "");
                    replace = replace.replace(" ", "");
                    replace = replace.replace(" ", "");

                    File des = new File(file, replace);
                    System.out.println(des);
                    src.renameTo(des);
                }

            } else {
                covertFold(src);
            }

        }
        System.out.println(files.length);
    }
}