package com.mundane.jianshucrawler.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {
    private static final int RUNNER_COUNT = 10;
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch begin = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(RUNNER_COUNT);
        final ExecutorService exec = Executors.newFixedThreadPool(10);

        for (int i = 0; i < RUNNER_COUNT; i++) {
            final int number = i;
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        // 这里是为了先挂起子线程, 让运动员们先不要开始跑
                        // 等执行完Game Start之后的begin.countDown()才开始跑
                        begin.await();
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

        System.out.println("Game Start ...");
        begin.countDown();
        end.await();
        System.out.println("Game Over.");

        exec.shutdown();
    }
}
