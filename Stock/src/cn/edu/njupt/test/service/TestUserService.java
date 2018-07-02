package cn.edu.njupt.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.njupt.bean.StockUser;
import cn.edu.njupt.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })

public class TestUserService {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testQueryByNamePass() {
		String userName = "yld";
		String userPassword = "123456";
		StockUser u = userService.queryByNamePass(userName, userPassword);
		System.out.println(u);
	}
}
