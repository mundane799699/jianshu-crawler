package com.mundane.jianshucrawler.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest2 {
    private static final int RUNNER_COUNT = 10;
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch end = new CountDownLatch(RUNNER_COUNT);
        final ExecutorService exec = Executors.newFixedThreadPool(10);

        for (int i = 0; i < RUNNER_COUNT; i++) {
            final int number = i;
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long)(Math.random() * 10000));
                        System.out.println("No." + number + " arrived");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        end.countDown();
                    }
                }
            };
            exec.submit(run);
        }

        end.await();
        System.out.println("Game Over.");

        exec.shutdown();
    }
}
