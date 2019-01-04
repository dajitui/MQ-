package com.example.demo.task;


import com.example.demo.entity.message;
import com.example.demo.mq.JMSProducer;
import com.example.demo.service.messageService;
import com.netflix.discovery.converters.Auto;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ScheduledTasks {

    @Autowired
    private messageService messageService;

    @Autowired
    private JMSProducer jmsProducer;

    private static SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MMM-ddd HH:mm:ss");


    /**
     * 定时查看未消费且重发次数少于10次的消息，并发送到mq
     */
    @Scheduled(fixedRate = 60000,initialDelay = 100)
    public void a(){
        System.out.println("现在时间："+dateFormat.format(new Date())+"开始查找未消费且重新发送次数不超过10次的消息......");
        List<Map<String,Object>> list=messageService.getMessage();
        if(list.size()!=0) {
            for (Map map : list) {
                String queue = map.get("queue").toString();
                String message = map.get("message").toString();
                String id = map.get("id").toString();
                Map me = new HashMap();
                me.put("message", "\"" + message + "\"");
                me.put("id", "\"" + id + "\"");

                try {
                    Destination destination = new ActiveMQQueue(queue);
                    jmsProducer.sendMessage(destination, me.toString());
                    int result = messageService.updateMessage(id);
                    if (result == 0) {
                        System.out.println("更新message发送次数失败!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * 定时查看未消费且重发次数已经等于10次的消息，修改消息状态以及死亡时间
     */
    @Scheduled(fixedRate = 60000,initialDelay = 5000)
    public void b(){
        System.out.println("现在时间："+dateFormat.format(new Date())+"开始查找并更新已死亡的消息......");
        messageService.updateMessageStatusAndDate();
    }
}

