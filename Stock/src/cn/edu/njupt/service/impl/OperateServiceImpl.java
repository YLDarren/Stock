package cn.edu.njupt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.njupt.bean.StockOperate;
import cn.edu.njupt.bean.StockParts;
import cn.edu.njupt.bean.StockUser;
import cn.edu.njupt.dao.OperateDao;
import cn.edu.njupt.dao.PartsDao;
import cn.edu.njupt.dao.UserDao;
import cn.edu.njupt.service.OperateService;

/**
 * service层需要判断数据是否有效
 * @author admin
 */
@Service
public class OperateServiceImpl implements OperateService{
	
	@Autowired
	private OperateDao operateDao;
	
	@Autowired
	private PartsDao partsDao;
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * 管理员根据零件编号更改临界值
	 * @param partsNumber 零件编号
	 * @param partsLimit 临界值
	 * @return
	 * 思路：
	 * 	0、先判断用户是否合法
	 * 	1、更改临界值
	 * 	2、判断剩余是否小于临界值
	 * 	3、如果小于，产生一条管理员操作信息，追加默认值;要更新两个表
	 * 	4、否则，不做任何处理
	 */
	public int managerUpdateLimit(String username , String partsNumber, int partsLimit) {
		StockUser stockUser = userDao.queryByName(username);
		if(stockUser == null) {
			//没有该用户，直接返回
			return 0;
		}
		if(partsLimit < 0) {
			//临界值不能小于0
			return 0;
		}
		int updateSuccess = partsDao.updateStockLimit(partsNumber, partsLimit);
		if(updateSuccess > 0 ) {
			//如果更新成功
			StockParts stockParts = partsDao.queryByNumber(partsNumber);
			int remain = stockParts.getPartsRemain();
			int limit = stockParts.getPartsLimit();
			if(remain < limit) {
				//剩余小于临界值;产生一条管理员操作信息，追加默认值
				StockOperate stockOperate = new StockOperate();
				stockOperate.setUserNumber( stockUser.getUserNumber() );
				stockOperate.setUserName( stockUser.getUserName() );
				stockOperate.setUserLevel( stockUser.getUserLevel() );
				stockOperate.setPartsNumber( stockParts.getPartsNumber() );
				stockOperate.setPartsMaterial( stockParts.getPartsMaterial() );
				stockOperate.setOperateActive( Byte.parseByte("1") );
				stockOperate.setOperateAdd( stockParts.getPartsDefaultAppend() );
				stockOperate.setOperateTake( 0 );
				
				int operateSuccess = operateDao.insertByStockOperate(stockOperate);
				if(operateSuccess > 0) {
					//更新零件表中的追加字段
					int appendSuccess = partsDao.managerUpdateAppend(stockParts.getPartsNumber() , stockParts.getPartsDefaultAppend() );
					if(appendSuccess > 0) {
						//更新零件表中的追加字段成功
						return 1;
					}else {
						//产生一条管理员操作信息成功，更新零件表中的追加字段失败
						return 0;
					}
				}else {
					//更新limit成功，但产生一条管理员操作信息失败
					return 0;
				}
			}else {
				return 1;
			}
		}else {
			//limit没有更新成功，且产生一条管理员操作信息失败
			return 0;
		}	
		
	}

	/**
	 * 管理员根据零件编号更改默认追加
	 * @param partsNumber 零件编号
	 * @param partsDefaultAppend 默认追加
	 * @return
	 * 思路：
	 * 	0、先判断用户是否合法
	 * 	1、更改默认追加
	 * 	
	 */
	public int managerUpdateDefaultAppend(String username , String partsNumber , int partsDefaultAppend) {
		StockUser stockUser = userDao.queryByName(username);
		if(stockUser == null) {
			//没有该用户，直接返回
			return 0;
		}
		
		//更改默认追加
		int updateSuccess = partsDao.updateStockDefaultAppend(partsNumber , partsDefaultAppend);
		
		if(updateSuccess > 0 ) {
			//更改默认追加成功
			return 1;
		}
		//更改默认追加失败
		return 0;
	}

	/**
	 * 管理员根据零件编号追加
	 * @param partsNumber 零件编号 
	 * @param partsAppend 追加
	 * @return
	 * 思路：
	 * 	0、先判断用户是否合法
	 * 	1、产生一条管理员追加消息;更新两张表
	 */
	public int managerUpdateAppend(String username , String partsNumber , int partsAppend) {
		StockUser stockUser = userDao.queryByName(username);
		if(stockUser == null) {
			//没有该用户，直接返回
			return 0;
		}
		
		StockParts stockParts = partsDao.queryByNumber(partsNumber);
		if(stockParts == null) {
			//没有该类型的零件
			return 0;
		}
		
		//产生一条管理员操作信息，追加指定值
		StockOperate stockOperate = new StockOperate();
		stockOperate.setUserNumber( stockUser.getUserNumber() );
		stockOperate.setUserName( stockUser.getUserName() );
		stockOperate.setUserLevel( stockUser.getUserLevel() );
		stockOperate.setPartsNumber( stockParts.getPartsNumber() );
		stockOperate.setPartsMaterial( stockParts.getPartsMaterial() );
		stockOperate.setOperateActive(Byte.parseByte("1"));
		stockOperate.setOperateAdd( partsAppend );
		stockOperate.setOperateTake( 0 );
		
		int operateSuccess = operateDao.insertByStockOperate(stockOperate);
		if(operateSuccess > 0) {
			//更新零件表中的追加
			//更新零件表中的追加字段
			int appendSuccess = partsDao.managerUpdateAppend(stockParts.getPartsNumber() , partsAppend );
			if(appendSuccess > 0) {
				//更新零件表中的追加字段成功
				return 1;
			}else {
				//产生一条管理员操作信息成功，更新零件表中的追加字段失败
				return 0;
			}
		}else {
			//产生一条管理员操作信息失败
			return 0;
		}
		
	}

	/**
	 * 管理员根据零件编号追加并更改临界值
	 * @param partsNumber 零件编号
	 * @param partsAppend 追加
	 * @param partsLimit 临界值
	 * @return
	 * 思路：
	 * 	0、先判断用户是否合法
	 * 	1、只是单纯的更改临界值(不做剩余判断)
	 * 	2、产生一条管理员追加消息;更新两张表
	 */
	public int managerUpdateAppendLimit(String username , String partsNumber, int partsAppend, int partsLimit) {
		StockUser stockUser = userDao.queryByName(username);
		if(stockUser == null) {
			//没有该用户，直接返回
			return 0;
		}
		
		//只是单纯的更改临界值(不做剩余判断)
		int updateSuccess = partsDao.updateStockLimit(partsNumber, partsLimit);
		
		if(updateSuccess > 0) {
			//产生一条管理员追加消息
			return managerUpdateAppend(username , partsNumber , partsAppend);
		}else {
			//临界值没有更改成功，且产生一条管理员追加消息失败
			return 0;
		}
		
	}

	/**
	 * 管理员根据零件编号追加并更改默认追加
	 * @param partsNumber 零件编号
	 * @param partsDefaultAppend 默认追加
	 * @return
	 * 思路：
	 * 	0、先判断用户是否合法
	 * 	1、更改默认追加
	 * 	2、产生一条管理员追加消息
	 */
	public int managerUpdateAppendDefaultAppend(String username , String partsNumber, int partsAppend, int partsDefaultAppend) {
		StockUser stockUser = userDao.queryByName(username);
		if(stockUser == null) {
			//没有该用户，直接返回
			return 0;
		}
		
		//更改默认追加
		int updateSuccess = managerUpdateDefaultAppend(username , partsNumber , partsDefaultAppend);
		
		if(updateSuccess > 0) {
			//产生一条管理员追加消息
			return managerUpdateAppend(username , partsNumber , partsAppend);
		}else {
			//更改默认追加失败，且产生一条管理员追加消息失败
			return 0;
		}
	}

	/**
	 * 管理员根据零件编号更改临界值和默认追加
	 * @param partsNumber 零件编号
	 * @param partsLimit 临界值
	 * @param partsDefaultAppend 默认追加
	 * @return
	 * 思路：
	 * 	0、先判断用户是否合法
	 * 	1、更改临界值
	 *	2、默认追加
	 * 
	 */
	public int managerUpdateLimitDefaultAppend(String username , String partsNumber, int partsLimit, int partsDefaultAppend) {
		StockUser stockUser = userDao.queryByName(username);
		if(stockUser == null) {
			//没有该用户，直接返回
			return 0;
		}
		
		//更改默认追加
		int updateSuccess = managerUpdateDefaultAppend(username , partsNumber , partsDefaultAppend);
		
		if(updateSuccess > 0) {
			//更改临界值
			return managerUpdateLimit(username , partsNumber , partsLimit);
			
		}else {
			//更改默认追加失败
			return 0;
		}
		
	}

	/**
	 * 管理员根据零件编号追加并更改临界值和默认追加
	 * @param partsNumber 零件编号
	 * @param partsAppend 追加
	 * @param partsLimit 临界值
	 * @param partsDefaultAppend 默认追加
	 * @return
	 * 思路：
	 * 	0、先判断用户是否合法
	 * 	1、更改临界值(单纯)和默认追加
	 * 	2、产生一条追加消息;更新两张表
	 */
	public int managerUpdate(String username , String partsNumber, int partsAppend, int partsLimit, int partsDefaultAppend) {
		StockUser stockUser = userDao.queryByName(username);
		if(stockUser == null) {
			//没有该用户，直接返回
			return 0;
		}
		
		//更改临界值(单纯)和默认追加
		int updateSuccess = partsDao.updateStockLimitDefaultAppend(partsNumber, partsLimit, partsDefaultAppend);
		
		if(updateSuccess > 0) {
			//产生一条追加消息
			return managerUpdateAppend(username , partsNumber , partsAppend);
		}else {
			//更改临界值(单纯)和默认追加失败
			return 0;
		}
	
	}

	/**
	 * 采购员根据零件编号采购
	 * @param partsNumber 零件编号
	 * @param partsBuy 购买的零件(默认>=管理员的追加)
	 * @return
	 * 思路：
	 * 	0、先判断用户是否合法
	 * 	1、更改库存
	 * 	2、把管理员产生的所有追加消息标记为已执行;更新两张表
	 */
	public int buyerUpdate(String username , String partsNumber, int partsBuy) {
		StockUser stockUser = userDao.queryByName(username);
		if(stockUser == null) {
			//没有该用户，直接返回
			return 0;
		}
		
		StockParts stockParts = partsDao.buyerQueryByNumber(partsNumber);
		
		if(stockParts == null) {
			//没有该类型的零件
			return 0;
		}
		
		//分两种情况，一种是有追加且状态为0，一种是状态为1
		int total = stockParts.getPartsTotal();
		int remain = stockParts.getPartsRemain();
		int append = stockParts.getPartsAppend() - partsBuy;
		
		stockParts.setPartsTotal( (total + partsBuy) );
		stockParts.setPartsRemain( (remain + partsBuy) );
		
		if(append < 0) {
			stockParts.setPartsAppend(0);
		}else {
			stockParts.setPartsAppend(append);
		}
		
		//更改库存还需更新追加字段
		int updateSuccess = partsDao.updateStock(stockParts);
		
		if(updateSuccess > 0) {
			//更改库存成功,把管理员产生的所有追加消息标记为已执行
			int operateSuccess = operateDao.updateByManager();
			if(operateSuccess > 0) {
				//更改库存成功,且把管理员产生的所有追加消息标记为已执行成功
				return 1;
			}else {
				//把管理员产生的所有追加消息标记为已执行失败
				return 0;
			}
			
		}else {
			//更改库存失败
			return 0;
		}
		
	}

	/**
	 * 车间员工根据零件编号提取
	 * @param partsNumber 零件编号
	 * @param partsTake 提取
	 * @return
	 * 0、先判断用户是否合法
	 * 1、更新库存
	 * 2、判断剩余是否小于临界值
	 * 3、如果小于产生一条管理员追加消息
	 * 4、否则，反回
	 */
	public int employUpdateTake(String username , String partsNumber, int partsTake) {
		StockUser stockUser = userDao.queryByName(username);
		if(stockUser == null) {
			//没有该用户，直接返回
			return 0;
		}
		
		StockParts stockParts = partsDao.employQueryByNumber(partsNumber);
		if(stockParts == null) {
			//没有该类型的零件
			return 0;
		}
		int used = stockParts.getPartsUsed();
		int remain = stockParts.getPartsRemain();
	
		if( (remain - partsTake) >0) {
			stockParts.setPartsRemain( (remain - partsTake) );
			stockParts.setPartsUsed( (used + partsTake) );
		}else {
			//取完
			stockParts.setPartsRemain(0);
			stockParts.setPartsUsed( (used + remain) );
		}
		
		//更新库存
		int updateSuccess = partsDao.updateStock(stockParts);
		
		if(updateSuccess >0 && stockParts.getPartsActive() == 1) {
			//如果更新库存成功
			StockParts stockPartsOther = partsDao.queryByNumber(partsNumber);
			int remainOther = stockPartsOther.getPartsRemain();
			int limit = stockPartsOther.getPartsLimit();
			
			if(remainOther < limit) {
				//产生一条管理员操作信息，追加默认值;更新两张表(需完善)
				StockOperate stockOperate = new StockOperate();
				stockOperate.setUserNumber( stockUser.getUserNumber() );
				stockOperate.setUserName( stockUser.getUserName() );
				stockOperate.setUserLevel( stockUser.getUserLevel() );
				stockOperate.setPartsNumber( stockPartsOther.getPartsNumber() );
				stockOperate.setPartsMaterial( stockPartsOther.getPartsMaterial() );
				stockOperate.setOperateActive(Byte.parseByte("1"));
				stockOperate.setOperateAdd( stockPartsOther.getPartsDefaultAppend() );
				stockOperate.setOperateTake( 0 );
				
				int operateSuccess = operateDao.insertByStockOperate(stockOperate);
				if(operateSuccess > 0) {
					//更新零件表
					int appendSuccess = partsDao.employUpdateAppend(partsNumber, stockPartsOther.getPartsDefaultAppend());
					if(appendSuccess > 0) {
						return 1;
					}else {
						//更新零件表失败
						return 0;
					}
					
				}else {
					//更新库存成功，但产生一条管理员操作信息失败
					return 0;
				}
			}else {
				return 1;
			}
		}else if(updateSuccess >0){
			//更新库存成功
			return 1;
		}else {
			return 0;
		}
	
	}

	/**
	 * 车间员工根据零件编号还需(一般表明只需多少件后就可停购)
	 * @param partsNumber 零件编号
	 * @param partsNeed 还需
	 * @return
	 * 思路：
	 * 	0、先判断用户是否合法
	 * 	1、产生一条车间员工还需多少的消息
	 * 	2、把该零件标记为停购
	 */
	public int employUpdateNeed(String username , String partsNumber, int partsNeed) {
		StockUser stockUser = userDao.queryByName(username);
		if(stockUser == null) {
			//没有该用户，直接返回
			return 0;
		}
		
		StockParts stockParts = partsDao.queryByNumber(partsNumber);
		if(stockParts == null) {
			//没有该类型的零件
			return 0;
		}
		
		//把该零件标记为停购
		int stopSuccess = partsDao.updateStopStock(partsNumber);
		
		if(stopSuccess >0) {
			//产生一条车间员工还需多少的消息(默认也是管理员信息，不再交由管理员手动添加)
			StockOperate stockOperate = new StockOperate();
			stockOperate.setUserNumber( stockUser.getUserNumber() );
			stockOperate.setUserName( stockUser.getUserName() );
			stockOperate.setUserLevel( stockUser.getUserLevel() );
			stockOperate.setPartsNumber( stockParts.getPartsNumber() );
			stockOperate.setPartsMaterial( stockParts.getPartsMaterial() );
			stockOperate.setOperateActive(Byte.parseByte("1"));
			stockOperate.setOperateAdd( partsNeed );
			stockOperate.setOperateTake( 0 );
			
			int operateSuccess = operateDao.insertByStockOperate(stockOperate);
			if(operateSuccess > 0) {
				//更新零件表中的追加字段
				int appendSuccess = partsDao.employUpdateAppend(stockParts.getPartsNumber() , stockParts.getPartsDefaultAppend() );
				if(appendSuccess > 0) {
					//更新零件表中的追加字段成功
					return 1;
				}else {
					//产生一条管理员操作信息成功，更新零件表中的追加字段失败
					return 0;
				}
			}else {
				//产生一条车间员工还需多少的消息失败
				return 0;
			}
		}else {
			//把该零件标记为停购失败
			return 0;
		}
	}

	/**
	 * 车间员工根据零件编号提取并还需
	 * @param partsNumber 零件编号
	 * @param partsTake 提取
	 * @param partsNeed 还需
	 * @return
	 * 思路：
	 * 	0、先判断用户是否合法
	 * 	1、更新库存
	 * 	2、产生一条车间员工还需多少的消息
	 * 	3、把该零件标记为停购
	 */
	public int employUpdate(String username , String partsNumber, int partsTake, int partsNeed) {
		StockUser stockUser = userDao.queryByName(username);
		if(stockUser == null) {
			//没有该用户，直接返回
			return 0;
		}
		
		StockParts stockParts = partsDao.employQueryByNumber(partsNumber);
		if(stockParts == null) {
			//没有该类型的零件
			return 0;
		}
		int used = stockParts.getPartsUsed();
		int remain = stockParts.getPartsRemain() ;
		
		
		if( (remain - partsTake) >0) {
			stockParts.setPartsRemain( (remain - partsTake) );
			stockParts.setPartsUsed( (used + partsTake) );
		}else {
			//取完
			stockParts.setPartsRemain(0);
			stockParts.setPartsUsed( (used + remain) );
		}
		
		//更新库存
		int updateSuccess = partsDao.updateStock(stockParts);
		
		if(updateSuccess > 0 && stockParts.getPartsActive() == 1) {
			//把该零件标记为停购
			int stopSuccess = partsDao.updateStopStock(partsNumber);
			
			if(stopSuccess >0) {
				//产生一条车间员工还需多少的消息(默认也是管理员信息，不再交由管理员手动添加)
				StockOperate stockOperate = new StockOperate();
				stockOperate.setUserNumber( stockUser.getUserNumber() );
				stockOperate.setUserName( stockUser.getUserName() );
				stockOperate.setUserLevel( stockUser.getUserLevel() );
				stockOperate.setPartsNumber( stockParts.getPartsNumber() );
				stockOperate.setPartsMaterial( stockParts.getPartsMaterial() );
				stockOperate.setOperateActive(Byte.parseByte("1"));
				stockOperate.setOperateAdd( partsNeed );
				stockOperate.setOperateTake( 0 );
				
				int operateSuccess = operateDao.insertByStockOperate(stockOperate);
				if(operateSuccess > 0) {
					//更新零件表中的追加字段
					int appendSuccess = partsDao.employUpdateAppend(stockParts.getPartsNumber() , stockParts.getPartsDefaultAppend() );
					if(appendSuccess > 0) {
						//更新零件表中的追加字段成功
						return 1;
					}else {
						//产生一条管理员操作信息成功，更新零件表中的追加字段失败
						return 0;
					}
				}else {
					//产生一条车间员工还需多少的消息失败
					return 0;
				}
			}else {
				//把该零件标记为停购失败
				return 0;
			}
		}else if(updateSuccess >0){
			//更新库存成功
			return 1;
		}else {
			return 0;
		}
	
	}

	/**
	 * 管理员停购
	 */
	public int managerStop(String username, String partsNumber) {
		StockUser stockUser = userDao.queryByName(username);
		if(stockUser == null) {
			//没有该用户，直接返回
			return 0;
		}
		
		StockParts stockParts = partsDao.queryByNumber(partsNumber);
		if(stockParts == null) {
			//没有该类型的零件
			return 0;
		}
		
		//停购
		int updateSuccess = partsDao.updateStopStock( stockParts.getPartsNumber() );
		
		if(updateSuccess > 0) {
			return 1;
		}else {
			//停购失败
			return 0;
		}

	}

	
}
