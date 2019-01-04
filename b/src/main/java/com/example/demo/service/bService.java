package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @program: demoA
 * @description
 * @author: dajitui
 * @create: 2019-01-03 02:20
 **/
@Service
public class bService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

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
}
