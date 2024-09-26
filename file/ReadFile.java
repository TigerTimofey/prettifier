package file;

import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

    public String readFile(String path) {
        try {
            FileReader reader = new FileReader(path);
            int data = reader.read();
            String fileContent = "";

            while (data != -1) {
                fileContent = fileContent.concat(String.valueOf((char) data));
                data = reader.read();
            }

            return fileContent;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
