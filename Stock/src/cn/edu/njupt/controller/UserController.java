package cn.edu.njupt.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.njupt.bean.StockUser;
import cn.edu.njupt.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//跳转到登陆框
	@RequestMapping(value = "/login" , method = RequestMethod.GET)
	public String login() {
		return "/page/login.html";
	}
	
	//登陆事件，有数据的验证
	@RequestMapping(value = "/login" , method = RequestMethod.POST)
	public void Login(HttpServletRequest request , HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		//设置返回字段的编码方式
		response.setContentType("text/html;charset=utf8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username == "" || password == "") {
			//如果username或password有一个为空就直接返回
			try {
				response.getWriter().write("用户名或者密码不能为空！");
			} catch (IOException e) {
				System.out.println(e);
			}
			return;
		}
		
		//查询数据库，获取用户
		StockUser user = userService.queryByNamePass(username, password);
	
		if(user == null) {
			//如果没有该用户，直接返回
			try {
				response.getWriter().write("该用户好像还没注册哦！");
			} catch (IOException e) {
				System.out.println(e);
			}
			return;
		}
		
		//把用户名和level存到session
		HttpSession session = request.getSession();
		session.setAttribute("username", user.getUserName());
		session.setAttribute("level", user.getUserLevel());
		
		//把用户名和level存到cookie中
		Cookie cookie = new Cookie("username" , URLEncoder.encode(user.getUserName(), "utf8"));
		cookie.setMaxAge(604800);
		cookie.setPath("/");
		
		Cookie cookie1 = new Cookie("level" , user.getUserLevel()+"");
		cookie1.setMaxAge(604800);
		cookie1.setPath("/");
		
		response.addCookie(cookie);
		response.addCookie(cookie1);
		
		//重定向到首页
		try {
			response.sendRedirect("/Stock/");
		} catch (IOException e) {
			try {
				response.getWriter().write("网络异常，请稍后再试！");
			} catch (IOException e1) {
				System.out.println(e1);
			}
			System.out.println(e);
		}
	}
	
	//退出登陆
	@RequestMapping(value = "/exit" , method = RequestMethod.POST)
	public void exit(HttpServletRequest request , HttpServletResponse response) throws IOException {
		//清掉session
		HttpSession session = request.getSession();
		session.setAttribute("username", null);
		session.setAttribute("level", null);
		
		//清掉cookie
		Cookie cookie = new Cookie("username" , null);
		cookie.setPath("/");
		
		Cookie cookie1 = new Cookie("level" , null);
		cookie1.setPath("/");
		
		response.addCookie(cookie);
		response.addCookie(cookie1);
		
		response.getWriter().write("{\"success\" : true}");
	}
	
}
