package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task2 {
    static List<Double> randomNumber = new ArrayList();

    public static void main(String[] args) throws InterruptedException {

        Task2 task2 = new Task2();
        Random randomValue = new Random();
        Thread firstThread = new Thread(() -> {
            for (int i=0; i<100000000;i++){
                synchronized (randomNumber) {
                    task2.randomNumber.add(randomValue.nextDouble());
                }

            }
        });

        Thread secondThread = new Thread(() -> {
            synchronized (randomNumber) {
                System.out.println(randomNumber.stream().reduce(Double.valueOf(0), (a, b) -> a + b));
            }

        });

        Thread thirdThread = new Thread(() -> {
            synchronized (randomNumber) {
                System.out.println(randomNumber.stream().reduce(Double.valueOf(0), (a, b) -> Math.pow(a, 2) + Math.pow(b, 2)));
            }

        });

        firstThread.start();
        secondThread.start();
        thirdThread.start();

        firstThread.join();
        secondThread.join();
        thirdThread.join();

        System.out.println("Finish!");

    }

}
