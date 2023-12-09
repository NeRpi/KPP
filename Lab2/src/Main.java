import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class Main {
    public static void main(String[] args) {
        Tasks Tasks = new Tasks();
        Tasks.createZip("./Lab2/src/");
        System.out.println(Tasks.getCountWordsFromFile("./Lab2/src/test.txt"));
        System.out.println(Tasks.reverseArray(new ArrayList<>(Arrays.asList("строкак", 1, 3.12))) + "\nШвец А.М. " + new Date());
    }
}