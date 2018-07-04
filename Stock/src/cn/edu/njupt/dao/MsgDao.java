package cn.edu.njupt.dao;

import java.util.List;

import cn.edu.njupt.bean.StockOperate;

/**
 * 消息dao层接口
 * 只面向采购员
 * @author admin
 *
 */
public interface MsgDao {
	//查询所有追加消息，即状态为1且有追加
	List<StockOperate> queryAll();
	
	//标记某个零件的所有追加为已经执行
	int updateActive(String partsNumber);
}
