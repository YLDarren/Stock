package cn.edu.njupt.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.njupt.bean.StockUser;
import cn.edu.njupt.dao.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class TestUserDao {

	@Autowired
	private UserDao userDao;
	
	@Test
	public void testQueryById() {
		StockUser u = userDao.queryById(2);
		System.out.println(u);
	}
	
	@Test
	public void testQueryByNamePass() {
		String userName = "yld";
		String userPassword = "1234567";
		StockUser u = userDao.queryByNamePass(userName, userPassword);
		System.out.println(u);
	}
}
