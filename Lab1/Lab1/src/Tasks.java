import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Tasks {
    int getCountWordsFromFile(String fileName) {
        int countWords = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                countWords += line.trim().split(" ").length;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countWords;
    }
    ArrayList<Object> reverseArray(ArrayList<Object> arr) {
        ArrayList<Object> newArr = new ArrayList<>(arr);
        Collections.reverse(newArr);
        return newArr;
    }
}
