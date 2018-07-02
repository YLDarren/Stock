package cn.edu.njupt.dao;

import java.util.List;

import cn.edu.njupt.bean.StockOperate;

/*
 * 操作Dao
 */
public interface OperateDao {
	
	//根据用户级别来查找
	List<StockOperate> queryByUserLevel(int userLevel);
	
	//根据StockOperate插入数据
	int insertByStockOperate(StockOperate stockOperate);
	
	//标记指定id的为已操作
	int updateById(int operateId);
	
	//标记管理员的为已操作
	int updateByManager();
	
	//标记车间员工的为已操作
	int updateByEmploy();
	
	
	
}
