package com.example.demo.service;

import com.example.demo.service.impl.MyFeginSericeImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: demoA
 * @description
 * @author: dajitui
 * @create: 2019-01-02 20:49
 **/
@FeignClient(value = "messageserver",fallback = MyFeginSericeImpl.class)
public interface MyFeginSerice {
    @RequestMapping(value = "/message/custom")
    public int updatemessagedate(@RequestParam(value = "id")String id);
}
