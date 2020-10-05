package ru.geekbrains.java_two.lesson_b;

import java.io.IOException;


    public class Exceptions_lesson2 {
    private static String LINE = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0";

        public static void main(String[] args) throws MyException {
            String[][] stringArr = ConvertStringLine.getStringArr(LINE);
            SumArray.SumArrayEqual2(stringArr);
        }

        public static class SumArray {
            static int SumArrayEqual2 (String [][] newArr) throws MyException {
                int sum = 0;
                int [][] newArrInt = new int[newArr.length][newArr.length];
                if (newArr.length != 4) {
                    throw new MyException();
                }
                try {
                    for (int i = 0; i < newArrInt.length*newArrInt.length; i++) {
                 try {
                     newArrInt[i/newArrInt.length][i%newArrInt.length] = Integer.parseInt(newArr[i/newArr.length][i%newArr.length]);
                 } catch (NumberFormatException e){
                     
                 }
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
                System.out.println(sum/2);
                return sum / 2;
            }
        }

        public static class MyException extends Exception {
            public MyException() {
                super();
                System.err.println("Требуемая длина массива 4x4");
            }
        }

        public static class ConvertStringLine {
            static String [][] getStringArr(String line) throws MyException  {
                String[] firstArr = line.split("\n");
                String[][] secondArr = new String[firstArr.length][];
                for (int i = 0; i < firstArr.length; i++) {
                    secondArr[i] = firstArr[i].split(" ");
                }
                if (firstArr.length != 4) {
                    throw new MyException();
                }
                return  secondArr;
            }
        }
    }




