package cn.edu.njupt.service;

import java.util.List;

import cn.edu.njupt.bean.SearchParts;
import cn.edu.njupt.bean.StockParts;

public interface PartsService {

	//根据库存编号查询单个零件
	StockParts queryByNumber(String partsNumber , byte level);
	
	// 根据条件类查询零件(SearchParts)
	List<StockParts> queryBySearch(SearchParts searchParts);

	// 查询所有的零件
	List<StockParts> queryAll();
	
	//添加一个零件
	int insertStockParts(StockParts stockParts);
}
