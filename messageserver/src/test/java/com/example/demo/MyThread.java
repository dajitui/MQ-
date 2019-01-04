package com.example.demo;

import com.example.demo.RedisUtil.Rlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: demoA
 * @description
 * @author: dajitui
 * @create: 2019-01-03 23:47
 **/
@Component
public class MyThread extends Thread{

    @Autowired
    Rlock rlock;

    @Override
    public void run() {
        if(rlock.lock("1")){
            A.f();
            System.out.println(A.a);
        }

        rlock.unlock("1");
    }
}
