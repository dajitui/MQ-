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
public class messageService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List getMessage(){
        return jdbcTemplate.queryForList("SELECT * FROM message WHERE STATUS = 0 AND sendcount<=10");
    }

    public int updateMessage(String id){
        //SQL+结果`
        int resRow = jdbcTemplate.update("UPDATE message SET sendcount=sendcount+1 WHERE id=?", new PreparedStatementSetter() {
            //映射
            // 数据
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, id);
            }
        });
        //返回结果
        return resRow;
    }

    public int updateMessageStatusAndDate(){
        //SQL+结果`
        int resRow = jdbcTemplate.update("UPDATE message SET status = 2 , diedate = NOW() where sendcount=10 ", new PreparedStatementSetter() {
            //映射
            // 数据
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                //ps.setString(1, id);
            }
        });
        //返回结果
        return resRow;
    }

    public int updateMessageCustomerDate(String id){
        //SQL+结果`
        int resRow = jdbcTemplate.update("UPDATE message SET customerdate=NOW() , STATUS=1 WHERE id = ?", new PreparedStatementSetter() {
            //映射
            // 数据
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, id);
            }
        });
        //返回结果
        return resRow;
    }
}
