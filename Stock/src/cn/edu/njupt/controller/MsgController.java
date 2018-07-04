package cn.edu.njupt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.njupt.bean.StockOperate;
import cn.edu.njupt.dto.PartsData;
import cn.edu.njupt.service.MsgService;

@Controller
public class MsgController {

	@Autowired
	private MsgService msgService;

	@RequestMapping(value = "/initMsg", method = RequestMethod.POST, produces = {
	"application/json;charset=UTF-8" })
	@ResponseBody
	public PartsData< List<StockOperate> > getBuyMsg(HttpServletRequest request) {
		PartsData<List<StockOperate>>  result = new PartsData<List<StockOperate>> (); 
		
		// 获取Session
		HttpSession session = request.getSession();
		Byte level = (Byte) session.getAttribute("level");
		
		if(level == 2) {
			//如果是采购员才查询消息
			List<StockOperate> list = msgService.queryAll();
			if(list != null) {
				result.setSuccess(true);
				result.setData(list);
			}else {
				result.setSuccess(false);
				result.setReason("服务异常，请稍后再试！");
			}
		}else {
			result.setSuccess(false);
			result.setReason("不是采购员！");
		}
		
		return result;
	}

}
