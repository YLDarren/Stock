package cn.edu.njupt.service;

import java.util.List;

import cn.edu.njupt.bean.StockOperate;

/**
 * msg的service层
 * @author admin
 */
public interface MsgService {
	
	//查询所有追加消息，即状态为1且有追加
	public List<StockOperate> queryAll();
	
	//标记某个零件的所有追加为已经执行
	int updateActive(String partsNumber);
}
