package cn.edu.njupt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.njupt.bean.SearchParts;
import cn.edu.njupt.bean.StockParts;

/*
 * 零件dao
 */
public interface PartsDao {
	
	//根据id查询单个零件
	StockParts queryById(int partsId);
	
	//根据库存编号查询单个零件
	StockParts queryByNumber(String partsNumber);
	
	//车间员工根据编号查询单个零件
	StockParts employQueryByNumber(String partsNumber);
	
	//采购员根据编号查询单个零件
	StockParts buyerQueryByNumber(String partsNumber);
	
	//根据条件类查询零件(SearchParts)
	List<StockParts> queryBySearch(SearchParts searchParts);
	
	//查询所有的零件
	List<StockParts> queryAll();
	
	//添加一个零件
	int insertStockParts(StockParts stockParts);
	
	//根据零件编号，更新库存
	int updateStock(StockParts stockParts);
	
	//根据零件编号，更新追加(管理员手动追加/底层管理员消息)
	int managerUpdateAppend(@Param("partsNumber") String partsNumber ,@Param("partsAppend") int partsAppend);
	
	//根据编号，更新追加(采购员)
	int employUpdateAppend(@Param("partsNumber") String partsNumber ,@Param("partsAppend") int partsAppend);
	
	//根据零件编号，停购某零件
	int updateStopStock(String partsNumber);
	
	//根据零件编号，更改临界值
	int updateStockLimit(@Param("partsNumber") String partsNumber ,@Param("partsLimit") int partsLimit);
	
	//根据零件编号，更改默认追加
	int updateStockDefaultAppend(@Param("partsNumber") String partsNumber ,@Param("partsDefaultAppend") int partsDefaultAppend);
	
	//根据零件编号，更改临界值和默认追加
	int updateStockLimitDefaultAppend(@Param("partsNumber") String partsNumber ,@Param("partsLimit") int partsLimit ,@Param("partsDefaultAppend") int partsDefaultAppend);
	
	
}
