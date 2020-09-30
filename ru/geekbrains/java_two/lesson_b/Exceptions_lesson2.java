package ru.geekbrains.java_two.lesson_b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

    public class Exceptions_lesson2 {

        public static void main(String[] args) throws MyException, IOException {
            System.out.println(SumArray.SumArrayEqual2(changeStringToArr("10 3 1 2 2 3 2 2 5 6 7 1 300 3 1 0")));
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder stringBuilder = new StringBuilder();
            System.out.println("Введите через Enter 16 цифр и получите сумму этих цифр деленную на два.");
            for (int i = 0; i < 16; i++) {
                stringBuilder.append(reader.readLine()).append(" ");
            }
            String s = stringBuilder.toString();
            System.out.println(SumArray.SumArrayEqual2(changeStringToArr(s)));
        }

        static String [][] changeStringToArr(String s) throws MyException {
            String[] arr = s.split(" ");
            if (arr.length != 16) {
                throw new MyException(s);
            }
            String[][] newArr = new String[4][4];
            for (int i = 0; i < newArr.length * newArr.length; i++) {
                newArr[i / newArr.length][i % newArr.length] = arr[i];
            }
            return newArr;
        }

        public static class SumArray {
            static int SumArrayEqual2 (String [][] newArr) throws MyException {
                int sum = 0;
                int j = 0;
                int [][] newArrInt = new int[newArr.length][newArr.length];
                if (newArr.length != 4) {
                    throw new MyException("Как у тебя получилось? Неверный размер массива))");
                }
                try {
                    for (int i = 0; i < newArrInt.length*newArrInt.length; i++) {
                        newArrInt[i/newArrInt.length][i%newArrInt.length] = Integer.parseInt(newArr[i/newArr.length][i%newArr.length]);
                        sum += newArrInt[i/newArrInt.length][i%newArrInt.length];
                    }
                } catch (NumberFormatException input) {
                    System.err.println("Wrong input. In line mast be numbers!!!");
                } catch (ArrayIndexOutOfBoundsException input) {
                    System.err.println("Ошибка индексации массива");
                } catch (RuntimeException input){
                    System.err.println("Runtime error");
                } catch (Exception e){
                    System.err.println("nothing2");
                }
                return sum / 2;
            }
        }

        public static class MyException extends Exception {
            public MyException(String s) {
                super();
                System.err.println("Неверный размер массива!!! Требуемая длина строки 16 чисел, через пробел.");
            }
        }
    }


