package com.company;

import java.util.concurrent.ConcurrentHashMap;

public class Main {
    ConcurrentHashMap<Integer, Integer> threadMap = new ConcurrentHashMap<>();
    int sum = 0;
    int index = 0;

    public static void main(String[] args) throws InterruptedException {

        Main mainThread = new Main();

        Thread firstThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                mainThread.threadMap.put(i, i);
            }
        });


        Thread secondThread = new Thread(() -> {
            for (int m : mainThread.threadMap.values()) {
                if (m > mainThread.index) {
                    mainThread.sum += m;
                    mainThread.index += 1;
                }

            }
        });

        firstThread.start();
        secondThread.start();

        firstThread.join();
        secondThread.join();

        System.out.println(mainThread.sum);
    }
}
