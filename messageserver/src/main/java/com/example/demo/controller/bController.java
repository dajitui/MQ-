package com.example.demo.controller;

import com.example.demo.service.messageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @program: demoA
 * @description
 * @author: dajitui
 * @create: 2018-12-31 17:04
 **/
@RestController
public class bController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private messageService messageService;

    @RequestMapping(value = "/message")
    public int b(@RequestParam(value = "message")String message,@RequestParam(value = "queue")String queue,@RequestParam(value = "sendsystem")String sendsystem,@RequestParam(value = "customersystem")String customersystem){

            String id= UUID.randomUUID().toString();

            String sendcount="0";

            //等待消费
            String status="0";

            //创建时间
        //创建时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date=format.format(new Date());

            String diecount="0";

            //SQL+结果
            int resRow = jdbcTemplate.update("INSERT INTO `message`(`id`, `message`, `sendcount`, `queue`, `sendsystem`, `status`, `customersystem`, `cdate`, `diecount`) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?);", new PreparedStatementSetter() {
                //映射
                // 数据
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setString(1, id);
                    ps.setString(2,message);
                    ps.setString(3,sendcount);
                    ps.setString(4,queue);
                    ps.setString(5,sendsystem);
                    ps.setString(6,status);
                    ps.setString(7,customersystem);
                    ps.setString(8, date);
                    ps.setString(9,diecount);

                }
            });
            //返回结果
            return resRow;

    }

    @RequestMapping(value = "/message/update")
    public int updatemessage(@RequestParam(value = "id")String id){
        return messageService.updateMessage(id);
    }

    @RequestMapping(value = "/message/custom")
    public int updatemessagedate(@RequestParam(value = "id")String id){
        return messageService.updateMessageCustomerDate(id);
    }
}
