package com.example.demo;

import com.example.demo.entity.A;
import com.example.demo.service.MyFeginSerice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private MyFeginSerice myFeginSerice;

	@Test
	public void cc(){
		int i=myFeginSerice.updatemessagedate("42f33dcf-c175-4d09-a7b2-0043dfffb58d");
		System.out.println(i);
	}

	@Test
	public void contextLoads() {
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
		System.out.println(resRow);
	}



}

