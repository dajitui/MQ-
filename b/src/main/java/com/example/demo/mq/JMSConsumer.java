package com.example.demo.mq;

import com.example.demo.service.MyFeginSerice;
import com.example.demo.service.bService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @program: demoA
 * @description
 * @author: dajitui
 * @create: 2019-01-03 14:18
 **/
@Component
public class JMSConsumer {
    @Autowired
    private MyFeginSerice myFeginSerice;

    private static Set set=new HashSet();

    @Autowired
    private bService bService;

    private final static Logger logger = LoggerFactory.getLogger(JMSConsumer.class);

    @JmsListener(destination = "message")
    @Transactional
    public void receiveQueue(String msg) {
        logger.info("接收到消息：{}",msg);
        JSONObject jasonObject = JSONObject.fromObject(msg);
        Map map = (Map)jasonObject;
        String id=map.get("id").toString();
        String message=map.get("message").toString();
        if(message.equals("b系统减一")){
            if(!set.contains(id)){
                set.add(id);
                bService.b();
                int result=myFeginSerice.updatemessagedate(id);
                if(result==0){
                    logger.error("更新消息消费时间失败！");
                }
            }
        }
    }
}