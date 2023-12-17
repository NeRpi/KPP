package org.example;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        System.out.println("Task #1\n---------------------------------------------");
        ArrayList<String> dirs = new ArrayList<>();
        File file = new File(scanner.nextLine());
        iterateDirectories(dirs, file);
        dirs.forEach(System.out::println);
        System.out.println("Task #2\n---------------------------------------------");
        String text = scanner.nextLine();
        String[] wordsArray = text.split("[^\\p{L}\\p{Nd}]+|\\s+");
        HashSet<String> words = new HashSet<>(Arrays.asList(wordsArray));
        HashMap<String, Integer> wordsMap = new HashMap<>();
        for(var word: words){
            int count = Collections.frequency(List.of(wordsArray), word);
            wordsMap.put(word, count);
        }
        for(var entry: wordsMap.entrySet()){
            System.out.println("Word: " + entry.getKey() + " Count: " + entry.getValue() + "\n");
        }

    }
    public static void iterateDirectories(ArrayList<String> dirs, File dir) {
        dirs.add(dir.getAbsolutePath());
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    iterateDirectories(dirs, file);
                }
            }
        }
    }
}

