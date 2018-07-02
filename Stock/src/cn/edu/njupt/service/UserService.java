package cn.edu.njupt.service;

import cn.edu.njupt.bean.StockUser;

public interface UserService {
	
	/**
	 * 根据用户名和密码查询用户
	 * @param userName
	 * @param userPassword
	 * @return
	 */
	StockUser queryByNamePass(String userName , String userPassword);
}
