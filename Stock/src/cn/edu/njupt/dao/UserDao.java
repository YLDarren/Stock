package cn.edu.njupt.dao;

import org.apache.ibatis.annotations.Param;

import cn.edu.njupt.bean.StockUser;

public interface UserDao {
	
	//根据id查询用户
	StockUser queryById(int userId);
	
	//根据用户名查询用户
	StockUser queryByName(String userName);
	
	//根据用户编号查询用户
	StockUser queryByNumber(String userNumber);
	
	//根据用户名和密码查询用户
	StockUser queryByNamePass(@Param("userName") String userName , @Param("userPassword") String userPassword);
	
	
	
}
