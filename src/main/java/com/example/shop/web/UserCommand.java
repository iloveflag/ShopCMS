package com.example.shop.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.shop.dao.UserDAO;
import com.example.shop.model.User;
import com.example.shop.util.Command;

public class UserCommand implements Command {
	private static Logger logger = LoggerFactory.getLogger(UserCommand.class);

	private static interface Views {        
        public static final String LOGIN = "/login.jsp";
        public static final String REGISTER = "/register.jsp";
        public static final String SHOOPINGCAET_LIST = "/cart.do?cmd=querycart";
        public static final String INDEX = "/index.jsp";
    } 

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求参数的字符编码
		request.setCharacterEncoding("UTF-8");
		String cmd = request.getParameter("cmd");
		if ("login".equals(cmd)) {  // 请求注销登陆
			return login(request, response);
		} else if ("logout".equals(cmd)) {  // 请求注销登陆
			return logout(request, response);
		} else if ("register".equals(cmd)) {  // 请求注册新用户
			return register(request, response);
		} else {
			logger.error("缺少请求参数cmd。");
			return Views.INDEX;
		}
	}
	
	private String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// 验证用户ID和密码是否合法
		if(password==""||username==""){
			return Views.LOGIN;
		}
		UserDAO userDao = new UserDAO();
		User user = userDao.queryUser(username, password);
		String message = "用户名或密码错误！";
		if (user == null) {
			request.setAttribute("message", message);
			return Views.LOGIN;
		}
		// 登录成功，设置session中的属性
		session.setAttribute("user", user);
		return Views.INDEX;
	}
	
	/**
	 * 处理用户注册
	 */
	private String register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(password==""||username==""){
			return Views.REGISTER;
		}
		// 验证表单输入数据合法性，略...
		// 验证用户是否存在
		String message = "账户名已经存在！";
		// 验证输入的账户名是否已经存在，现在改为查询数据库
		UserDAO userDao = new UserDAO();
		User u = userDao.queryUser(username, null);
		if (u != null) {
			request.setAttribute("message", message);
			return Views.REGISTER;
		}
		// 验证通过，将用户数据添加到数据库
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		// 保存数据
		if (userDao.insert(user)) {  // 保存成功，转到文件列表页面
			request.getSession().setAttribute("user", user);
			// 注册成功，转到文件列表页面
			return Views.INDEX;
		} else {  // 保存失败
			request.setAttribute("message", "注册失败。");
			return Views.REGISTER;
		}
	}

	private String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) session.invalidate();
		// 设置页面的提示信息
		request.setAttribute("message", "您已成功退出登录！");
		return Views.INDEX;
	}
}
