package cn.edu.njupt.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.njupt.dto.PartsData;
import cn.edu.njupt.service.OperateService;

/**
 * 操作controller
 * Controller层判断数据是否合法
 * @author admin
 */
@Controller
public class OperateController {
	
	@Autowired
	private OperateService operateService;
	
	//管理员编辑
	@RequestMapping(value = "/manager", method = RequestMethod.POST, produces = {
	"application/json;charset=UTF-8" })
	@ResponseBody
	public PartsData<String> managerOperate(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		
		PartsData<String> result = new PartsData<String>();
		
		//先从session域中拿到username和level
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		Byte level = (Byte) session.getAttribute("level");
		
		if(username.equals("") || level != 1) {
			result.setSuccess(false);
			result.setReason("服务异常，请刷新");
		}
		
		String partsNumber = request.getParameter("number").replaceAll("\\s+", "");
		String partsLimit = request.getParameter("limit").replaceAll("\\s+", "");
		String partsAppend = request.getParameter("append").replaceAll("\\s+", "");
		String partsDefaultAppend = request.getParameter("defaultAppend").replaceAll("\\s+", "");
		
		if(partsNumber.equals("")) {
			//如果编号为空
			result.setSuccess(false);
			result.setReason("服务异常，请刷新");
		}
		
		if(partsLimit.equals("") && partsAppend.equals("") && partsDefaultAppend.equals("")) {
			//如果都为空，直接返回
			result.setSuccess(true);
			result.setData("没有做任何操作");
		}else if(!partsLimit.equals("") && partsAppend.equals("") && partsDefaultAppend.equals("")){
			//如果临界值不为空，其他为空
			try {
				int limit = Integer.parseInt(partsLimit);
				int updateSuccess = operateService.managerUpdateLimit(username, partsNumber, limit);
				if(updateSuccess > 0) {
					result.setSuccess(true);
					result.setData("临界值更新成功！");
				}else {
					result.setSuccess(false);
					result.setReason("该零件已经停购！不能再更新临界值");
				}
			}catch(Exception e) {
				//如果有异常
				result.setSuccess(false);
				result.setReason("数据格式不对，请重新操作！");
			}
			
		}else if(partsLimit.equals("") && !partsAppend.equals("") && partsDefaultAppend.equals("")) {
			//如果追加不为空，其他为空
			try {
				int append = Integer.parseInt(partsAppend);
				int appendSuccess = operateService.managerUpdateAppend(username, partsNumber, append);
				if(appendSuccess > 0) {
					result.setSuccess(true);
					result.setData("追加更新成功！");
				}else {
					result.setSuccess(false);
					result.setReason("该零件已经停购！不能再追加");
				}
			}catch(Exception e) {
				//如果有异常
				result.setSuccess(false);
				result.setReason("数据格式不对，请重新操作！");
			}
			
		}else if(partsLimit.equals("") && partsAppend.equals("") && !partsDefaultAppend.equals("")) {
			//如果默认追加不为空，其他都为空
			try {
				int defaultAppend = Integer.parseInt(partsDefaultAppend);
				int defaultAppendSuccess = operateService.managerUpdateDefaultAppend(username, partsNumber, defaultAppend);
				if(defaultAppendSuccess > 0) {
					result.setSuccess(true);
					result.setData("默认追加更新成功！");
				}else {
					result.setSuccess(false);
					result.setReason("该零件已经停购！不能再更新默认追加");
				}
			}catch(Exception e) {
				//如果有异常
				result.setSuccess(false);
				result.setReason("数据格式不对，请重新操作！");
			}
		}else if(!partsLimit.equals("") && !partsAppend.equals("") && partsDefaultAppend.equals("")) {
			//如果临界值不为空且追加不为空，其他为空
			try {
				int limit = Integer.parseInt(partsLimit);
				int append = Integer.parseInt(partsAppend);
				
				int limtAppendSuccess = operateService.managerUpdateAppendLimit(username, partsNumber, append, limit);
				
				if(limtAppendSuccess > 0) {
					result.setSuccess(true);
					result.setData("临界值和追加更新成功！");
				}else {
					result.setSuccess(false);
					result.setReason("该零件已经停购！不能再追加和更新临界值");
				}
			}catch(Exception e) {
				//如果有异常
				result.setSuccess(false);
				result.setReason("数据格式不对，请重新操作！");
			}
		}else if(!partsLimit.equals("") && partsAppend.equals("") && !partsDefaultAppend.equals("")) {
			//如果临界值不为空和默认追加不为空，其他为空
			try {
				int limit = Integer.parseInt(partsLimit);
				int defaultAppend = Integer.parseInt(partsDefaultAppend);
				
				int limitDefaultAppendSuccess = operateService.managerUpdateLimitDefaultAppend(username, partsNumber, limit, defaultAppend);
				
				if(limitDefaultAppendSuccess > 0) {
					result.setSuccess(true);
					result.setData("临界值和默认追加更新成功！");
				}else {
					result.setSuccess(false);
					result.setReason("该零件已经停购！不能再更新默认追加和更新临界值");
				}
			}catch(Exception e) {
				//如果有异常
				result.setSuccess(false);
				result.setReason("数据格式不对，请重新操作！");
			}
		}else if(partsLimit.equals("") && !partsAppend.equals("") && !partsDefaultAppend.equals("")) {
			//如果追加和默认追加不为空
			try {
				int append = Integer.parseInt(partsAppend);
				int defaultAppend = Integer.parseInt(partsDefaultAppend);
				
				int appendDefaultAppendSuccess = operateService.managerUpdateAppendDefaultAppend(username, partsNumber, append, defaultAppend);
				
				if(appendDefaultAppendSuccess > 0) {
					result.setSuccess(true);
					result.setData("追加和默认追加更新成功！");
				}else {
					result.setSuccess(false);
					result.setReason("该零件已经停购！不能再追加和更新默认追加");
				}
			}catch(Exception e) {
				//如果有异常
				result.setSuccess(false);
				result.setReason("数据格式不对，请重新操作！");
			}
		}else if(!partsLimit.equals("") && !partsAppend.equals("") && !partsDefaultAppend.equals("")) {
			//如果都不为空
			try {
				int limit = Integer.parseInt(partsLimit);
				int append = Integer.parseInt(partsAppend);
				int defaultAppend = Integer.parseInt(partsDefaultAppend);
				
				int update = operateService.managerUpdate(username, partsNumber, append, limit, defaultAppend);
				
				if(update > 0) {
					result.setSuccess(true);
					result.setData("更新成功！");
				}else {
					result.setSuccess(false);
					result.setReason("该零件已经停购！");
				}
			}catch(Exception e) {
				//如果有异常
				result.setSuccess(false);
				result.setReason("数据格式不对，请重新操作！");
			}
		}
		return result;
	}
	
	
	//采购员购买buyer
	@RequestMapping(value = "/buyer", method = RequestMethod.POST, produces = {
	"application/json;charset=UTF-8" })
	@ResponseBody
	public PartsData<String> buyerOperation(HttpServletRequest request) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		
		PartsData<String> result = new PartsData<String>();
		
		//先从session域中拿到username和level
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		Byte level = (Byte) session.getAttribute("level");
				
		if(username.equals("") || level != 2) {
			result.setSuccess(false);
			result.setReason("服务异常，请刷新");
		}
		
		String partsNumber = request.getParameter("number").replaceAll("\\s+", "");
		String partsBuy = request.getParameter("buy").replaceAll("\\s+", "");
		
		if(partsNumber.equals("")) {
			//如果编号为空
			result.setSuccess(false);
			result.setReason("服务异常，请刷新");
		}
		
		if(partsBuy.equals("")) {
			//如果编号为空
			result.setSuccess(false);
			result.setReason("没有执行任何操作");
		}else {
			try {
				int buy = Integer.parseInt(partsBuy);
				int buySuccess = operateService.buyerUpdate(username, partsNumber, buy);
				
				if(buySuccess > 0) {
					result.setSuccess(true);
					result.setData("购买成功！");
				}else {
					result.setSuccess(false);
					result.setReason("操作成功！");
				}
			}catch(Exception e) {
				//如果有异常
				result.setSuccess(false);
				result.setReason("数据格式不对，请重新操作！");
			}
			
		}
		
		return result;
	}
	
	
	//车间员工
	@RequestMapping(value = "/employ", method = RequestMethod.POST, produces = {
	"application/json;charset=UTF-8" })
	@ResponseBody
	public PartsData<String> employOperation(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		
		PartsData<String> result = new PartsData<String>();
		
		//先从session域中拿到username和level
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		Byte level = (Byte) session.getAttribute("level");
				
		if(username.equals("") || level != 3) {
			result.setSuccess(false);
			result.setReason("服务异常，请刷新");
		}
		
		String partsNumber = request.getParameter("number").replaceAll("\\s+", "");
		String partsExtract = request.getParameter("extract").replaceAll("\\s+", "");
		String partsNeed = request.getParameter("need").replaceAll("\\s+", "");
		
		if(partsNumber.equals("")) {
			//如果编号为空
			result.setSuccess(false);
			result.setReason("服务异常，请刷新");
		}
		
		if(partsExtract.equals("") && partsNeed.equals("")) {
			result.setSuccess(false);
			result.setReason("没有执行任何操作");
		}else if(!partsExtract.equals("") && partsNeed.equals("")) {
			//如果提取不为空，need为空
			try {
				int extract = Integer.parseInt(partsExtract);
				
				int extractSuccess = operateService.employUpdateTake(username, partsNumber, extract);
				
				if(extractSuccess > 0) {
					result.setSuccess(true);
					result.setData("取货成功！");
				}else {
					result.setSuccess(false);
					result.setReason("该零件没有货了！");
				}
			}catch(Exception e) {
				//如果有异常
				result.setSuccess(false);
				result.setReason("数据格式不对，请重新操作！");
			}
			
		}else if(partsExtract.equals("") && !partsNeed.equals("")) {
			//如果need不为空，提取为空
			try {
				int need = Integer.parseInt(partsNeed);
				
				int needSuccess = operateService.employUpdateNeed(username, partsNumber, need);
				
				if(needSuccess > 0) {
					result.setSuccess(true);
					result.setData("need成功！");
				}else {
					result.setSuccess(false);
					result.setReason("该零件已经停购！");
				}
			}catch(Exception e) {
				//如果有异常
				result.setSuccess(false);
				result.setReason("数据格式不对，请重新操作！");
			}
			
		}else if(!partsExtract.equals("") && !partsNeed.equals("")) {
			//如果提取和need都不为空
			try {
				int extract = Integer.parseInt(partsExtract);
				int need = Integer.parseInt(partsNeed);
				
				int extractNeedSuccess = operateService.employUpdate(username, partsNumber, extract, need);
				
				if(extractNeedSuccess > 0) {
					result.setSuccess(true);
					result.setData("提取和need成功！");
				}else {
					result.setSuccess(false);
					result.setReason("该零件没有了或已经停购！");
				}
			}catch(Exception e) {
				//如果有异常
				result.setSuccess(false);
				result.setReason("数据格式不对，请重新操作！");
			}
		}
		
		return result;
	}
	
	
	//停购
	@RequestMapping(value = "/stop", method = RequestMethod.POST, produces = {
	"application/json;charset=UTF-8" })
	@ResponseBody
	public PartsData<String> stopOperation(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		
		PartsData<String> result = new PartsData<String>();
		
		//先从session域中拿到username和level
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		Byte level = (Byte) session.getAttribute("level");
				
		if(username.equals("") || level != 1) {
			result.setSuccess(false);
			result.setReason("服务异常，请刷新");
		}
		
		String partsNumber = request.getParameter("number").replaceAll("\\s+", "");
		
		if(!partsNumber.equals("")) {
			int stopSuccess = operateService.managerStop(username, partsNumber);
			
			if(stopSuccess > 0) {
				result.setSuccess(true);
				result.setData("停购成功！");
			}else {
				result.setSuccess(false);
				result.setReason("停购失败！");
			}
		}else {
			result.setSuccess(false);
			result.setReason("服务异常，请刷新！");
		}
		
		return result;
	}

}
