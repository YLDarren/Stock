package cn.edu.njupt.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.njupt.bean.SearchParts;
import cn.edu.njupt.bean.StockParts;
import cn.edu.njupt.dto.PartsData;
import cn.edu.njupt.service.PartsService;

/*
 * 零件controller
 */
@Controller
public class PartsController {
	
	@Autowired
	private PartsService partsService;
	
	//初始化数据
	@RequestMapping(value = "/init", method = RequestMethod.POST, produces = {
	"application/json;charset=UTF-8" })
	@ResponseBody
	public PartsData< List<StockParts> > initData() {
		PartsData< List<StockParts> > result = new PartsData< List<StockParts> >();
		
		//查询所有的零件
		List<StockParts> list = partsService.queryAll();
		
		if(list == null) {
			//零件为空
			result.setSuccess(false);
			result.setReason("网络异常，请稍后再试!");
		}else {
			result.setSuccess(true);
			result.setData(list);
		}
		return result;
	}
	
	//根据条件类查询
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = {
	"application/json;charset=UTF-8" })
	@ResponseBody
	public PartsData< List<StockParts> > searchData(HttpServletRequest request) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
	
		PartsData< List<StockParts> > result = new PartsData< List<StockParts> >();
		SearchParts searchParts = new SearchParts();
		
		String partsNumber = request.getParameter("id").replaceAll("\\s+", "");//库存零件编号
		String partsMaterial = request.getParameter("name").replaceAll("\\s+", "");//库存零件名字
		String partsRemain = request.getParameter("remain").replaceAll("\\s+", "");//库存剩余
		String partsLimit = request.getParameter("limit").replaceAll("\\s+", "");//库存临界值
		String partsDefaultAppend = request.getParameter("append").replaceAll("\\s+", "");//库存默认追加
		
		
		//有可能会类型转化错误try一下
		try {
			if(!partsNumber.equals("")) {
				searchParts.setPartsNumber(partsNumber);
			}
			if(!partsMaterial.equals("")) {
				searchParts.setPartsMaterial(partsMaterial);
			}
			if(!partsRemain.equals("")) {
				searchParts.setPartsRemain(Integer.parseInt(partsRemain));
			}
			if(!partsLimit.equals("")) {
				searchParts.setPartsLimit(Integer.parseInt(partsLimit));
			}
			if(!partsDefaultAppend.equals("")) {
				searchParts.setPartsDefaultAppend(Integer.parseInt(partsDefaultAppend));
			}
		}catch(Exception e) {
			result.setSuccess(false);
			result.setReason("类型转化错误,输入数据不合法！");
			return result;
		}
		
		//向后台查询结果
		List<StockParts> list = partsService.queryBySearch(searchParts);
		
		if(list == null) {
			//零件为空
			result.setSuccess(false);
			result.setReason("网络异常，请稍后再试!");
		}else {
			result.setSuccess(true);
			result.setData(list);
		}
		
		return result;
	}
	
	//根据零件编号查询
	@RequestMapping(value = "/number", method = RequestMethod.POST, produces = {
	"application/json;charset=UTF-8" })
	@ResponseBody
	public PartsData<StockParts> searchByNumber(HttpServletRequest request) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		
		PartsData<StockParts> result = new PartsData<StockParts>();
		
		//获取session
		HttpSession session = request.getSession();
		byte level = (Byte) session.getAttribute("level");
		
		if(level < 0) {
			result.setSuccess(false);
			result.setReason("服务异常，请刷新");
		}
		
		String partsNumber = request.getParameter("number");
		
		if( partsNumber.equals("") ) {
			result.setSuccess(false);
			result.setReason("服务异常，请刷新");
		}else {
			StockParts stockParts = partsService.queryByNumber(partsNumber , level);
			if(stockParts == null) {
				result.setSuccess(false);
				result.setReason("该零件已经停购，只能取件");
			}else {
				result.setData(stockParts);
				result.setSuccess(true);
			}
		}
		return result;
	}
	
	
	//添加一条库存记录
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = {
	"application/json;charset=UTF-8" })
	@ResponseBody
	public PartsData<String> managerAdd(HttpServletRequest request) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		
		PartsData<String> result = new PartsData<String>();
		
		//获取session
		HttpSession session = request.getSession();
		byte level = (Byte) session.getAttribute("level");
		
		if(level != 1) {
			result.setSuccess(false);
			result.setReason("服务异常，请刷新");
		}
		
		String number = request.getParameter("number").replaceAll("\\s+" , "");
		String material = request.getParameter("material").replaceAll("\\s+" , "");
		String total = request.getParameter("total").replaceAll("\\s+" , "");
		String limit = request.getParameter("limit").replaceAll("\\s+" , "");
		String defaultAppend = request.getParameter("defaultAppend").replaceAll("\\s+" , "");
		
		if(!number.equals("") && !material.equals("") && !total.equals("")) {
			try {
				int partsTotal = Integer.parseInt(total);
				int partsLimit = 0;
				int partsDefaultAppend = 0;
				
				if(!limit.equals("")) {
					partsLimit = Integer.parseInt(limit);
				}
				
				if(!defaultAppend.equals("")) {
					partsDefaultAppend = Integer.parseInt(defaultAppend);
				}
				
				StockParts stockParts = new StockParts();
				stockParts.setPartsNumber(number);
				stockParts.setPartsMaterial(material);
				stockParts.setPartsTotal(partsTotal);
				stockParts.setPartsLimit(partsLimit);
				stockParts.setPartsDefaultAppend(partsDefaultAppend);
				
				int insertSuccess = partsService.insertStockParts(stockParts);
				
				if(insertSuccess > 0) {
					result.setSuccess(true);
					result.setData("添加成功！");
				}else {
					result.setSuccess(false);
					result.setData("添加失败，请重新尝试！");
				}
			}catch(Exception e) {
				//如果有异常
				result.setSuccess(false);
				result.setReason("数据格式错误，请重新添加！");
			}
			
			
		}else {
			result.setSuccess(false);
			result.setReason("字段不能为空");
		}
		
		return result;
	}
	
}
