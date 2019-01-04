package com.example.demo.SeMaPhoreTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Test {

    static CountDownLatch countDownLatch=new CountDownLatch(100);

    public static void main(String[] args) {

        Semaphore semaphore=new Semaphore(10);

        for(int i=0;i<100;i++){
            //System.out.println(i);
            countDownLatch.countDown();
            Thread thread=new MyThread(semaphore);
            thread.start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
