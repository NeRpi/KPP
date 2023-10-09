import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Tasks {
    void createZip(String dirPath) {
        File dir = new File(dirPath);
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(dirPath + "output.zip"));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(zout))) {
            zout.putNextEntry(new ZipEntry("resultFile.txt"));
            for (File fileTXT : Objects.requireNonNull(dir.listFiles((file, name) -> name.endsWith(".txt"))))
                writer.write(Files.readString(fileTXT.toPath()));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    int getCountWordsFromFile(String fileName) {
        try {
            return Files.readString(Paths.get(fileName)).trim().split("[\\s\n]+").length;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    ArrayList<Object> reverseArray(ArrayList<Object> arr) {
        ArrayList<Object> newArr = new ArrayList<>(arr);
        Collections.reverse(newArr);
        return newArr;
    }
}
