package ru.geekbrains.java_two.lesson_e;

/*
1. Необходимо написать два метода, которые делают следующее:

1) Создают одномерный длинный массив, например:
static final int size = 10000000;
static final int h = size / 2;
float[] arr = new float[size];
2) Заполняют этот массив единицами;
3) Засекают время выполнения: long a = System.currentTimeMillis();
4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
5) Проверяется время окончания метода System.currentTimeMillis();
6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);

Отличие первого метода от второго:
Первый просто бежит по массиву и вычисляет значения.
Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы
обратно в один.

Пример деления одного массива на два:
System.arraycopy(arr, 0, a1, 0, h);
System.arraycopy(arr, h, a2, 0, h);

Пример обратной склейки:
System.arraycopy(a1, 0, arr, 0, h);
System.arraycopy(a2, 0, arr, h, h);

Примечание:
System.arraycopy() – копирует данные из одного массива в другой:
System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника, массив-назначение, откуда начинаем записывать данные в массив-назначение, сколько ячеек копируем)
По замерам времени:

Для первого метода надо считать время только на цикл расчета:
for (int i = 0; i < size; i++) {
arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
}

Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и склейки.
 */

import java.util.Arrays;

public class lesson_e  extends Thread {
    static final int size = 10000000;
    static final int h = size / 2;


    @Override
    public void run() {
        while (!isInterrupted()) {
        try {
            sleep(1500);
            System.out.println(Thread.currentThread().getName() + ".thread is working");
        } catch (InterruptedException e) {
            interrupt();
        }
        }
    }

    public lesson_e(String name) {
        super(name);
    }

    private static float[] createArr(int size) {
        float[] arr = new float[size];
        Arrays.fill(arr, 1.0f);
        return arr;
    }

    public static void main(String[] args) {
        long a = arrTimeElapsed(createArr(size));
        long b = arrTimeElapsedTwoThreads(createArr(size));
        System.out.println("One thread time: " + a + "\nTwo threads time: " + b + "\nMore faster for: " + (a - b) + " ms.");
        }


    private static long arrTimeElapsed(float[] arr) {
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long timeMain = System.currentTimeMillis() - a;
        System.out.println("TimeElapsed with " + Thread.currentThread().getName() + ".thread: " + timeMain);
        return timeMain;
    }


    private static long arrTimeElapsedTwoThreads(float[] arr) {
        long a = System.currentTimeMillis();
        float [] a1 = new float[h];
        float [] a2 = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        long copy = System.currentTimeMillis() - a;
        System.out.println("TimeElapsed to copy arr: " + copy);

        lesson_e myThread = new lesson_e("MT1");
        myThread.start();
        a = System.currentTimeMillis();
        for (int i = 0; i < a1.length; i++) {
            a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long mt1 = System.currentTimeMillis() - a;
        if (myThread.isAlive() || myThread != null) {
            System.out.println("TimeElapsed to calculation in " + myThread.getName() + ".thread: " + mt1);
        }
        myThread.interrupt();

        lesson_e myThread2 = new lesson_e("MT2");
        myThread2.start();
        a = System.currentTimeMillis();
        for (int i = 0; i < a2.length; i++) {
            a2[i] = (float)(a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long mt2 = System.currentTimeMillis() - a;
        if (myThread2.isAlive() || myThread2 != null) {
            System.out.println("TimeElapsed to calculation in " + myThread2.getName() + ".thread: " + mt2);
        }
        myThread2.interrupt();

        a = System.currentTimeMillis();
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        long copy2 = System.currentTimeMillis() - a;
        System.out.println("TimeElapsed to coppy arr: "  + copy2);

        long timeTwoThreads = copy + mt1 + mt2 + copy2;
        System.out.println("TimeElapsed with 2 threads " + timeTwoThreads);

        if (!myThread.isAlive()) {
            System.out.println(myThread.getName() + ".thread closed");
        }
        if (!myThread2.isAlive()) {
            System.out.println(myThread2.getName() + ".thread closed");
        }

        return timeTwoThreads;
    }

}
