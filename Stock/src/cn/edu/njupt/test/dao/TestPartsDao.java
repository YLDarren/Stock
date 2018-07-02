package cn.edu.njupt.test.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.njupt.bean.SearchParts;
import cn.edu.njupt.bean.StockParts;
import cn.edu.njupt.dao.PartsDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class TestPartsDao {
	
	@Autowired
	private PartsDao partsDao;
	
	@Test
	public void testQueryById() {
		StockParts part = partsDao.queryById(2);
		System.out.println(part);
	}
	
	@Test
	public void testQueryBySearch() {
		SearchParts searchParts = new SearchParts();
		searchParts.setPartsNumber("10000001");
		searchParts.setPartsMaterial("木材");
		List<StockParts> list = partsDao.queryBySearch(searchParts);
		System.out.println(list);
	}
	
	@Test
	public void testQueryAll() {
		List<StockParts> list = partsDao.queryAll();
		System.out.println(list.size());
	}
	
	@Test
	public void testUpdateStock() {
		StockParts stockParts = partsDao.queryByNumber("10000001");
		
		int num = 1000;
		int total = stockParts.getPartsTotal();
		int remain = stockParts.getPartsRemain();
		int used = stockParts.getPartsUsed();
		
		total += num;
		remain += num;
		stockParts.setPartsTotal(total);
		stockParts.setPartsRemain(remain);
		//跟新库存
		partsDao.updateStock(stockParts);
	}
	
	@Test
	public void testUpdateStockLimit() {
		int a = partsDao.updateStockLimit("10000001", 2000);
		System.out.println(a);
	}
	
	@Test
	public void testUpdateStockDefaultAppend() {
		int a = partsDao.updateStockDefaultAppend("10000001", 2000);
		System.out.println(a);
	}
	
	@Test
	public void testUpdateStockLimitDefaultAppend() {
		int a = partsDao.updateStockLimitDefaultAppend("10000001", 3000, 5000);
		System.out.println(a);
	}
	
	
	@Test
	public void testManagerUpdateAppend() {
		String partsNumber = "10000005";
		int partsAppend = -500;
		int a = partsDao.managerUpdateAppend(partsNumber, partsAppend);
		System.out.println(a);
	}
	
}
