package cn.edu.njupt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.njupt.bean.StockUser;
import cn.edu.njupt.dao.UserDao;
import cn.edu.njupt.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	
	public StockUser queryByNamePass(String userName, String userPassword) {
		return userDao.queryByNamePass(userName, userPassword);
	}

}
