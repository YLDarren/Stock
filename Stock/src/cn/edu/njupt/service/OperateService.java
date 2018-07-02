package cn.edu.njupt.service;

public interface OperateService {
	
	/**
	 * 管理员根据零件编号更改临界值
	 * @param partsNumber 零件编号
	 * @param partsLimit 临界值
	 * @return
	 * 思路：
	 * 	0、先判断用户是否合法
	 * 	1、更改临界值
	 * 	2、判断剩余是否小于临界值
	 * 	3、如果小于，产生一条管理员操作信息，追加默认值
	 * 	4、否则，不做任何处理
	 */
	int managerUpdateLimit(String username , String partsNumber , int partsLimit);
	
	
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
	int managerUpdateDefaultAppend(String username , String partsNumber , int partsDefaultAppend);
	
	/**
	 * 管理员根据零件编号追加
	 * @param partsNumber 零件编号 
	 * @param partsAppend 追加
	 * @return
	 * 思路：
	 * 	0、先判断用户是否合法
	 * 	1、产生一条管理员追加消息
	 */
	int managerUpdateAppend(String username , String partsNumber , int partsAppend);
	
	/**
	 * 管理员根据零件编号追加并更改临界值
	 * @param partsNumber 零件编号
	 * @param partsAppend 追加
	 * @param partsLimit 临界值
	 * @return
	 * 思路：
	 * 	0、先判断用户是否合法
	 * 	1、只是单纯的更改临界值(不做剩余判断)
	 * 	2、产生一条管理员追加消息
	 */
	int managerUpdateAppendLimit(String username , String partsNumber , int partsAppend , int partsLimit );
	
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
	int managerUpdateAppendDefaultAppend(String username , String partsNumber , int partsAppend  ,int partsDefaultAppend);
	
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
	int managerUpdateLimitDefaultAppend(String username ,String partsNumber , int partsLimit , int partsDefaultAppend);
	
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
	 * 	2、产生一条追加消息
	 */
	int managerUpdate(String username , String partsNumber , int partsAppend , int partsLimit , int partsDefaultAppend);
	
	/**
	 * 管理员停购
	 * @param username
	 * @param partsNumber
	 * @return
	 */
	int managerStop(String username , String partsNumber);
	
	
	/**
	 * 采购员根据零件编号采购
	 * @param partsNumber 零件编号
	 * @param partsBuy 购买的零件(默认>=管理员的追加)
	 * @return
	 * 思路：
	 * 	0、先判断用户是否合法
	 * 	1、更改库存
	 * 	2、把管理员产生的所有追加消息标记为已执行
	 */
	int buyerUpdate(String username , String partsNumber , int partsBuy);
	
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
	int employUpdateTake(String username , String partsNumber , int partsTake);
	
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
	int employUpdateNeed(String username , String partsNumber , int partsNeed);
	
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
	int employUpdate(String username , String partsNumber , int partsTake , int partsNeed);
	
	
}
