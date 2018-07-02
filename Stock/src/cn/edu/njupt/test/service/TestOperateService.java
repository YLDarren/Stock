package cn.edu.njupt.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.njupt.service.OperateService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })
public class TestOperateService {
	
	@Autowired
	private OperateService operateService;
	
	//测试管理员根据零件编号更改临界值
	@Test
	public void testManagerUpdateLimit() {
		String username ="yld";
		String partsNumber = "10000001";
		int partsLimit = 1000;
		int success = operateService.managerUpdateLimit(username, partsNumber, partsLimit);
		
		System.out.println(success);
	}
	
	//测试管理员根据零件编号更改默认追加
	@Test
	public void testManagerUpdateDefaultAppend() {
		String username ="yld";
		String partsNumber = "10000001";
		int partsDefaultAppend = 1000;
		int success = operateService.managerUpdateDefaultAppend(username, partsNumber, partsDefaultAppend);
		
		System.out.println(success);
		//select * from stock_parts;
	}
	
	//测试管理员根据零件编号追加
	@Test
	public void testManagerUpdateAppend() {
		String username ="yld";
		String partsNumber = "10000001";
		int partsAppend = 1000;
		int success = operateService.managerUpdateAppend(username, partsNumber, partsAppend);
		
		System.out.println(success);
		//select * from stock_operate;
	}
	
	//测试管理员根据零件编号追加并更改临界值
	@Test
	public void testManagerUpdateAppendLimit() {
		String username ="yld";
		String partsNumber = "10000001";
		int partsAppend = 2000;
		int partsLimit = 2000;
		int success = operateService.managerUpdateAppendLimit(username, partsNumber, partsAppend , partsLimit);
		
		System.out.println(success);
		/**
		 * select * from stock_parts;
		 * select * from stock_operate;
		 */
	}
	
	//测试管理员根据零件编号追加并更改默认追加
	@Test
	public void testManagerUpdateAppendDefaultAppend() {
		String username ="yld";
		String partsNumber = "10000001";
		int partsAppend = 3000;
		int partsDefaultAppend = 2000;
		int success = operateService.managerUpdateAppendDefaultAppend(username, partsNumber, partsAppend , partsDefaultAppend);
		
		System.out.println(success);
		/**
		 * select * from stock_parts;
		 * select * from stock_operate;
		 */
	}
	
	//测试管理员根据零件编号更改临界值和默认追加
	@Test
	public void testManagerUpdateLimitDefaultAppend() {
		String username ="yld";
		String partsNumber = "10000001";
		int partsLimit = 3000;
		int partsDefaultAppend = 3000;
		int success = operateService.managerUpdateLimitDefaultAppend(username, partsNumber, partsLimit , partsDefaultAppend);
		
		System.out.println(success);
		/**
		 * select * from stock_parts;
		 */
	}
	
	//测试管理员根据零件编号追加并更改临界值和默认追加
	@Test
	public void testManagerUpdate() {
		String username ="yld";
		String partsNumber = "10000001";
		int partsLimit = 4000;
		int partsDefaultAppend = 4000;
		int partsAppend = 4000;
		int success = operateService.managerUpdate(username, partsNumber, partsAppend ,partsLimit , partsDefaultAppend);
		
		System.out.println(success);
		/**
		 * select * from stock_parts;
		 * select * from stock_operate;
		 */
	}
	
	//测试采购员根据零件编号采购
	@Test
	public void testBuyerUpdate() {
		String username ="yld";
		String partsNumber = "10000001";
		int partsBuy = 10000;
		int success = operateService.buyerUpdate(username, partsNumber, partsBuy);
		
		System.out.println(success);
		/**
		 * select * from stock_parts;
		 * select * from stock_operate;
		 */
	}
	
	//测试车间员工根据零件编号提取
	@Test
	public void testEmployUpdateTake() {
		String username ="小天";
		String partsNumber = "10000001";
		int partsTake = 20000;
		int success = operateService.employUpdateTake(username, partsNumber, partsTake);
		
		System.out.println(success);
		/**
		 * select * from stock_parts;
		 * select * from stock_operate;
		 */
	}
	
	//测试车间员工根据零件编号还需(一般表明只需多少件后就可停购)
	@Test
	public void testEmployUpdateNeed() {
		String username ="小天";
		String partsNumber = "10000001";
		int partsNeed = 2000;
		int success = operateService.employUpdateNeed(username, partsNumber, partsNeed);
		
		System.out.println(success);
		/**
		 * select * from stock_parts;
		 * select * from stock_operate;
		 */
	}
	
	//测试车间员工根据零件编号提取并还需
	@Test
	public void testEmployUpdate() {
		String username ="小天";
		String partsNumber = "10000002";
		int partsNeed = 3000;
		int partsTake = 9000;
		int success = operateService.employUpdate(username, partsNumber, partsTake , partsNeed);
		
		System.out.println(success);
		/**
		 * select * from stock_parts;
		 * select * from stock_operate;
		 */
	}
}
