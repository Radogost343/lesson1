package ru.geekbrains.java_two.lesson_c.searchForWord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Words  {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    final static String line = ("а б в г д е ё ж з и й к л м н о п р с т у ф х ц ч ш щ ъ ы ь э ю я a b c d e f g h i j k l m n o p q r s t u v w x y z 0 1 2 3 4 5 6 7 8 9 , + - * / ! № % : ? * _ ");
    static String [] wordsArr = createArr();
    static ArrayList<String> stringArray = convertArrToArrayList(wordsArr);
    static HashMap<String,Integer> hashMap = convertArrayToHashMap(stringArray);

    private static String [] createArr() {
        return Words.line.split(" ");
    }

    private static ArrayList<String> convertArrToArrayList (String [] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }

    static HashMap<String,Integer> convertArrayToHashMap (ArrayList<String> stringArray) {
        Words.stringArray = stringArray;
        HashMap<String, Integer> hashMap= new HashMap<>();
        for (String s : stringArray) {
            hashMap.put(s, 0);
        }
        return hashMap;
    }

    static void textContainsWords (String userInput) {

        String[] wordsArr = userInput.toLowerCase().split("");
        for (String s : wordsArr) {
            if (hashMap.containsKey(s)) {
            hashMap.put(s, hashMap.get(s) + 1);
            }
        }

        for (String key : hashMap.keySet()) {
            if (hashMap.get(key) != 0) {
                System.out.println(key);
            }
        }

        for (String key : hashMap.keySet()) {
            Integer value = hashMap.get(key);
            if (hashMap.get(key) != 0) {
                System.out.println(key + " <--> " + value);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Введите строку, я выведу какие буквы используются в данной строке.");
        String userInput = reader.readLine();
        textContainsWords(userInput);
    }

}


