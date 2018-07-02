package cn.edu.njupt.test.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.njupt.bean.StockOperate;
import cn.edu.njupt.dao.OperateDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class TestOperateDao {
	
	@Autowired
	private OperateDao operateDao;
	
	@Test
	public void testInsertByStockOperate() {
		StockOperate stockOperate = new StockOperate();
		Byte b = new Byte("2");
		stockOperate.setUserNumber("10002");
		stockOperate.setUserName("test");
		stockOperate.setUserLevel(b);
		stockOperate.setPartsNumber("100005");
		stockOperate.setPartsMaterial("测试3");
		stockOperate.setOperateAdd(1000);
		stockOperate.setOperateTake(0);
		stockOperate.setOperateActive(Byte.parseByte("1"));
		
		operateDao.insertByStockOperate(stockOperate);	
	}
	
	@Test
	public void testQueryByUserLevel() {
		List<StockOperate> list = operateDao.queryByUserLevel(1);
		System.out.println(list);
	}
	
	@Test
	public void testUpdateById() {
		int a = operateDao.updateById(3);
		System.out.println(a);
	}
	
	
	@Test
	public void testUpdateByManager() {
		int a = operateDao.updateByManager();
		System.out.println(a);
	}
	
	@Test
	public void testUpdateByEmploy() {
		int a = operateDao.updateByEmploy();
		System.out.println(a);
	}
	
}
