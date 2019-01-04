package com.example.demo.controller;

import com.example.demo.service.MyFeginSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    private MyFeginSerice myFeginSerice;

    @RequestMapping(value = "/b")
    public int b(){


            //SQL+结果
            int resRow = jdbcTemplate.update("UPDATE b SET thing=thing+1 WHERE id=1", new PreparedStatementSetter() {
                //映射
                // 数据
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    //ps.setInt(1, id);
                }
            });
            //返回结果
            return resRow;

    }

    @RequestMapping(value = "/b/test")
    public void a(){
        int i=myFeginSerice.updatemessagedate("42f33dcf-c175-4d09-a7b2-0043dfffb58d");
        System.out.println(i);
    }
}
