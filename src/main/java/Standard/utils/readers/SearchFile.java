package Standard.utils.readers;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;

public class SearchFile {

    public static String getAbsolutePath(String fileName) {

        File root = new File("./src/");

        String[] extensions = {"sql"};
        boolean isRecursive = true;

        Collection<?> files = FileUtils.listFiles(root, extensions, true);

        String relativePath = null;

        for (Iterator<?> iterator = files.iterator(); iterator.hasNext(); ) {
            File file = (File) iterator.next();
            if (file.getName().toLowerCase().contains(fileName.toLowerCase())) {
                relativePath = file.getPath();
                break;
            }
        }

        return relativePath;
    }

}
