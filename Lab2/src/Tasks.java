import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


// Класс со всеми заданиями
public class Tasks {
	
	// Функция для создания архива
    void createZip(String dirPath) {
    	// Отрываем соответсвующую директорию
        File dir = new File(dirPath);

        // Блок try для обработки ошибок с потоками и объектами Null
        // В скобочках открываются потоки для записи в архив
        // Таким образом, эти потоки самостоятельно закроются по окончанию блока try
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(dirPath + "output.zip"));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(zout))) {
        	// Создаём файл в арфиве
            zout.putNextEntry(new ZipEntry("resultFile.txt"));
            
            // Проходимя с помощью конструкции for each по каждому файлу внутри нащей директории и передаём содержимое в поток для записи
            // Object.requireNonNull - гарантирует что мы не получим объет Null
            // listFiles - возвращает файлы, а указанная конструкция внутри скобок, фильтрует полученный список фалов
            // endsWith(".txt") - функция возвращает True, если строка заканчивается на ".txt". В нашем случае, строка это название файла.
            for (File fileTXT : Objects.requireNonNull(dir.listFiles((file, name) -> name.endsWith(".txt"))))
                writer.write(Files.readString(fileTXT.toPath()));
        } catch (IOException | NullPointerException e) {
        	// Выводим ошибку
            e.printStackTrace();
        }
    }

    // Функция для подсчёта слов в тексте
    int getCountWordsFromFile(String fileName) {
    	// Блок try для обработки ошибок при работе с файлами
        try {
        	// Открываем поток для чтения по полученному пути к файлу.
        	// В результате получаем строку у которой методом trim удаляет лишние пробелы в начале и в конце строки
        	// Методом split делем строку по тем элементам что указаны в регулярном выражении, то есть по любому кодичеству пробелов или энтеров
        	// (Знаки '\s' - это пробел, '\n' - переход на новую строку, '+' - говорит о том что их может быть любое количество)
            return Files.readString(Paths.get(fileName)).trim().split("[\s\n]+").length;
        } catch (IOException e) {
        	// Выводим ошибку
            e.printStackTrace();
            return -1;
        }
    }

    // Функция для переварачивания строки
    ArrayList<Object> reverseArray(ArrayList<Object> arr) {
    	// Создаём копию полученного массива 
    	// И с помощь стандартного метода библиотеки Collections переварачиваем новый массив
        ArrayList<Object> newArr = new ArrayList<>(arr);
        Collections.reverse(newArr);
        return newArr;
    }
}
