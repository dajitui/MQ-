package com.example.demo;

import com.example.demo.entity.A;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void contextLoads() {
		//SQL+结果
		int resRow = jdbcTemplate.update("UPDATE a SET thing=thing-1 WHERE id=1", new PreparedStatementSetter() {
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

