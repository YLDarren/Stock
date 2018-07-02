package cn.edu.njupt.test.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.njupt.bean.SearchParts;
import cn.edu.njupt.bean.StockParts;
import cn.edu.njupt.service.PartsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })

public class TestPartsService {
	
	@Autowired
	private PartsService partsService;
	
	@Test
	public void testQueryBySearch() {
		SearchParts searchParts = new SearchParts();
		searchParts.setPartsMaterial("芯片");
		List<StockParts> list = partsService.queryBySearch(searchParts);
		System.out.println(list);
	}
	
	@Test
	public void testQueryAll() {
		List<StockParts> list = partsService.queryAll();
		System.out.println(list.size());
	}
	
}
