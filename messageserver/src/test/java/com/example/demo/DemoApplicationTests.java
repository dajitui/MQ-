package com.example.demo;

import com.example.demo.RedisUtil.Rlock;
import com.example.demo.entity.message;
import com.example.demo.mq.JMSProducer;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisPool;

import javax.jms.Destination;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private com.example.demo.service.messageService messageService;

	@Test
	public void contextLoads() {
		//SQL+结果`
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


	@Test
	public void c(){
			//SQL+结果`
			int resRow = jdbcTemplate.update("UPDATE message SET customerdate= NOW() , status=1 WHERE id=?", new PreparedStatementSetter() {
				//映射
				// 数据
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, "0aa79dba-fa05-4276-8835-6fda5a456488");
				}
			});
			//返回结果
		System.out.println(resRow);
	}

	@Test
	public  void v(){
			//System.out.println(messageService.getMessage());
		List<Map<String,Object>> list=messageService.getMessage();
		for(Map map:list){
			System.out.println(map.get("id"));
		}
	}

	@Test
	public void b(){
		String id= UUID.randomUUID().toString();

		String sendcount="0";

		//等待消费
		String status="0";

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
				String message="";
				ps.setString(2,message);
				ps.setString(3,sendcount);
				String queue="";
				ps.setString(4,queue);
				String sendsystem="";
				ps.setString(5,sendsystem);
				ps.setString(6,status);
				String customersystem="";
				ps.setString(7,customersystem);
				ps.setString(8,  date);
				ps.setString(9,diecount);

			}
		});
		//返回结果
		System.out.println(resRow);
	}

    @Autowired
    private JMSProducer jmsProducer;

    @Test
    public void testJms() {
        Destination destination = new ActiveMQQueue("springboot.queue.test");

        for (int i=0;i<10;i++) {
            jmsProducer.sendMessage(destination,"hello,world!" + i);
        }
    }


	@Test
	public void testa(){
		CountDownLatch countDownLatch=new CountDownLatch(100);

		for(int i=0;i<100;i++){
			//countDownLatch.countDown();

			/*MyThread myThread=new MyThread();
			myThread.start();*/

			new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        if (rlock.lock("1")){
                            System.out.println("获取到锁，开始执行减一操作");
                            A.f();
                            System.out.println(A.a);
                            if(rlock.unlock("1")){
                                System.out.println("释放锁成功");
                                break;
                            }else {
                                System.out.println("释放锁失败!!!");
                            }
                        }else {
                            System.out.println("获取不到锁，等待......");
                            int random= (int) (Math.random()*10000);
                            try {
                                Thread.sleep(random);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    /*if (rlock.lock("1")){
                        System.out.println("获取到锁，开始执行减一操作");
                        A.f();
                        System.out.println(A.a);
                        if(rlock.unlock("1")){
                            System.out.println("释放锁成功");
                            //break;
                        }else {
                            System.out.println("释放锁失败!!!");
                        }
                    }else{
                        System.out.println("获取不到锁，等待2秒......");
                        int random= (int) (Math.random()*10000);
                        try {
                            Thread.sleep(1000+ random);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (rlock.lock("1")){
                            A.f();
                            System.out.println(A.a);
                            if(rlock.unlock("1")){
                                System.out.println("释放锁成功");
                                //break;
                            }else {
                                System.out.println("释放锁失败!!!");
                            }
                        }else {
                            //System.out.println("获取不到锁.......");
                            try {
                                Thread.sleep(random);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (rlock.lock("1")){
                                A.f();
                                System.out.println(A.a);
                                if(rlock.unlock("1")){
                                    System.out.println("释放锁成功");
                                    //break;
                                }else {
                                    System.out.println("释放锁失败!!!");
                                }
                            }else {
                                System.out.println("获取不到锁.......");
                            }
                        }
                    }*/
                }
            }).start();

		}
		/*try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/

    }

	@Autowired
	Rlock rlock;

    @Test
    public void test(){
        int random= (int) (Math.random()*1000);
        double b= Math.random()*1000;
        System.out.println(random+" "+b);
    }

}

