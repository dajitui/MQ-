package com.example.demo.controller;

import com.example.demo.service.MyFeginSerice;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.demo.QueueUtil.queue;

/**
 * @program: demoA
 * @description
 * @author: dajitui
 * @create: 2018-12-31 17:01
 **/
@RestController
public class aController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MyFeginSerice myFeginSerice;

    @RequestMapping(value = "/a")
    @Transactional
    public int a(){

            //SQL+结果
            int resRow = jdbcTemplate.update("UPDATE a SET thing=thing-1 WHERE id=1", new PreparedStatementSetter() {
                //映射
                // 数据
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    //ps.setInt(1, id);4
                }
            });


            if(resRow==1){
                myFeginSerice.b("b系统减一",queue,"a服务","b服务");
                return 1;
            }

            return 0;

    }

}
