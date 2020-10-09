package ru.geekbrains.java_two.lesson_d;

import javax.swing.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static java.lang.String.format;

public class ChatServer {
    public void start(int port) {
        System.out.println("Server started at: " + port);
    }


    public void stop() {
        System.out.println("Server stopped");
    }

    public static void addMessage(String message,String userName, JTextArea log) {
        log.append(format("%s\n%s\n",userName,message));
         try {
             (new PrintStream(new FileOutputStream("log", true))).print(format("%s\n%s\n",userName,message));
         } catch (FileNotFoundException e) {
             System.out.println("Файл не найден");
         }
    }
}