package com.company;

import java.util.Arrays;

public class Main {
    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;



    public static void main(String[] args) {
        float[] arr = new float[SIZE];
        firstArrayMethod(arr);
        secondArrayMethod(arr);
    }


    //первый метод
    public static void firstArrayMethod(float[] array){
        array = new float[SIZE];
        for (int i = 0; i < array.length; i++) {
            array[i] = 1;
//            System.out.println(array[i]);
        }
        countValueAndTime(array);
    }


    //Замер времени и замена значений массива для первого метода
    public static void countValueAndTime(float[] array) {
        long a = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            array[i] = (float)(array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
//            System.out.println(array[i]);
        }
        long b = System.currentTimeMillis();
        System.out.println("Время работы первого метода: " + (b - a));
    }

    //Второй метод
    public static void secondArrayMethod(float[] array){
        array = new float[SIZE];
        for (int i = 0; i < array.length; i++) {
            array[i] = 1;
//            System.out.println(array[i]);
        }

        long a = System.currentTimeMillis();

        float[] array1 = new float[HALF];
        float[] array2 = new float[HALF];
        System.arraycopy(array, 0, array1, 0, HALF);
        System.arraycopy(array, HALF, array2, 0, HALF);


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                countValueAndTime2(array1);
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                countValueAndTime2(array2);
            }
        });
        thread1.start();
        thread2.start();

        System.arraycopy(array1, 0, array, 0, HALF);
        System.arraycopy(array2, 0, array, HALF, HALF);

        long b = System.currentTimeMillis();
        System.out.println("Время работы второго метода: " + (b - a));
    }

    //Замер времени и замена значений массива для второго метода
    public static void countValueAndTime2(float[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (float)(array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
//            System.out.println(array[i]);
        }
    }



}
