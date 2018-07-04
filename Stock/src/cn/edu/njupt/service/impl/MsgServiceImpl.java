package cn.edu.njupt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.njupt.bean.StockOperate;
import cn.edu.njupt.dao.MsgDao;
import cn.edu.njupt.service.MsgService;

@Service
public class MsgServiceImpl implements MsgService {

	@Autowired
	private MsgDao msgDao;
	
	//查询所有追加消息，即状态为1且有追加
	public List<StockOperate> queryAll() {
		return msgDao.queryAll();
	}

	//标记某个零件的所有追加为已经执行
	public int updateActive(String partsNumber) {
		return msgDao.updateActive(partsNumber);
	}

}
