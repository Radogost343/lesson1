package ru.geekbrains.java_two.lesson_c.searchForPhoneOrEmail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class PhoneBook {
    static HashMap<String, String> phoneEmail = new HashMap<>();
}

class Person {
    private String FIO;
    private String phone;
    private String email;

    public Person(String FIO, String phone, String email) {
        this.FIO = FIO;
        this.phone = phone;
        this.email = email;
    }

    static ArrayList<Person> CreateList() {
        ArrayList<Person> personArrayList = new ArrayList<>();
        Person person = new Person("Иванов", "916520", "Ivanov@mail.ru");
        Person person1 = new Person("Петров", "915521","Petrov@rambler.ru");
        Person person2 = new Person("Сидоров", "914522", "Sidorov@google.ru");
        Person person3 = new Person("Иванов", "913523", "Ivanov@icloud.com");
        personArrayList.add(person);
        personArrayList.add(person1);
        personArrayList.add(person2);
        personArrayList.add(person3);
        return personArrayList;
    }

    static void searchPhone(String userInput) {
        ArrayList<Person> person = CreateList();
        for (Person item : person) {
            PhoneBook.phoneEmail.put(item.FIO, item.phone);
        }
        for (String key : PhoneBook.phoneEmail.keySet()) {
            String value = PhoneBook.phoneEmail.get(key);
            if (key.equals(userInput)) {
                System.out.println(key + " <--> " + value);
            }
        }
        if (!PhoneBook.phoneEmail.containsKey(userInput)) {
            System.out.println("В списке нет данной фамилии");
        }
    }

    static void searchEmail(String userInput) {
        ArrayList<Person> persons = CreateList();
        for (Person person : persons) {
            PhoneBook.phoneEmail.put(person.FIO, person.email);
        }
        for (String key : PhoneBook.phoneEmail.keySet()) {
            String value = PhoneBook.phoneEmail.get(key);
            if (key.equals(userInput)) {
                System.out.println(key + " <--> " + value);
            }
        }
        if (!PhoneBook.phoneEmail.containsKey(userInput)) {
            System.out.println("В списке нет данной фамилии");
        }
    }
}

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите фамилию для поиска телефона: ");
        Person.searchPhone(reader.readLine());
        System.out.println("Введите фамилию для поиска email: ");
        Person.searchEmail(reader.readLine());
    }
}