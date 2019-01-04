package com.example.demo.service.impl;

import com.example.demo.service.MyFeginSerice;
import org.springframework.stereotype.Component;

/**
 * @program: demoA
 * @description
 * @author: dajitui
 * @create: 2019-01-02 21:14
 **/
@Component
public class MyFeginSericeImpl implements MyFeginSerice {
    @Override
    public int b(String message, String queue, String sendsystem, String customersystem) {
        return -1;
    }
}
