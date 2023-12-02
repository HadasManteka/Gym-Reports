package Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class PrintToFile {

    public static void write(String path, String data) throws IOException {
        File myObj = new File(path);
        if (!myObj.createNewFile()) {
            myObj.createNewFile();
        }
        java.io.FileWriter myWriter = new java.io.FileWriter(path, StandardCharsets.UTF_8);
        myWriter.write(data);
        myWriter.close();
    }
}
