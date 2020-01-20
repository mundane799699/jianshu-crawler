package com.mundane.jianshucrawler.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Parallellimit {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        CountDownLatch cdl = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            CountRunnable runnable = new CountRunnable(cdl);
            pool.execute(runnable);
        }
    }

    static class CountRunnable implements Runnable {
        private CountDownLatch countDownLatch;
        public CountRunnable(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }
        @Override
        public void run() {
            try {
                synchronized (countDownLatch) {
                    Thread.sleep((long)(Math.random() * 100));
                    // 每次减少一个容量
                    countDownLatch.countDown();
                    System.out.println("thread counts = " + (countDownLatch.getCount()));
                }
                if (countDownLatch.getCount() == 0) {
                    System.out.println("concurrency counts = " + (100 - countDownLatch.getCount()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
