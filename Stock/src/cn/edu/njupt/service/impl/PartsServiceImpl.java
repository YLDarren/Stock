package cn.edu.njupt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.njupt.bean.SearchParts;
import cn.edu.njupt.bean.StockParts;
import cn.edu.njupt.dao.PartsDao;
import cn.edu.njupt.service.PartsService;

@Service
public class PartsServiceImpl implements PartsService{
	
	@Autowired
	private PartsDao partsDao;
	

	// 根据条件类查询零件(SearchParts)
	public List<StockParts> queryBySearch(SearchParts searchParts) {
		return partsDao.queryBySearch(searchParts);
	}

	// 查询所有的零件
	public List<StockParts> queryAll() {
		return partsDao.queryAll();
	}

	//根据零件编号查询零件
	public StockParts queryByNumber(String partsNumber , byte level) {
		if(level == 3) {
			return partsDao.employQueryByNumber(partsNumber);
		}else if(level == 2){
			return partsDao.buyerQueryByNumber(partsNumber);
		}else {
			return partsDao.queryByNumber(partsNumber);
		}
		
	}

	//添加一个库存零件
	public int insertStockParts(StockParts stockParts) {
		return partsDao.insertStockParts(stockParts);
	}

}
