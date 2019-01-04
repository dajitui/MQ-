package com.example.demo.mq;

import com.example.demo.service.messageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;


/**
 * @program: demoA
 * @description
 * @author: dajitui
 * @create: 2019-01-03 14:17
 **/
@Component
public class JMSProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private messageService messageService;

    public void sendMessage(Destination destination, String message) {
        this.jmsTemplate.convertAndSend(destination,message);
    }
}
