package com.example.demo.SeMaPhoreTest;

import java.util.Date;
import java.util.concurrent.Semaphore;

public class MyThread extends Thread{

    Semaphore semaphore;

    public MyThread(Semaphore semaphore) {
        this.semaphore=semaphore;
    }

    @Override
    public void run() {
        super.run();
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("start:"+Thread.currentThread().getName()+" "+new Date());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end:"+Thread.currentThread().getName()+" "+new Date());

        semaphore.release();
    }
}
