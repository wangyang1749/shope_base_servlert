package com.shop.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.GoodsDaoImpl;
import com.shop.dao.IGoodsDao;
import com.shop.dao.IUserDao;
import com.shop.dao.UserDaoImpl;
import com.shop.model.Goods;
import com.shop.model.Page;
import com.shop.model.User;
import com.shop.utils.UserException;
import com.sun.prism.shader.Solid_LinearGradient_PAD_AlphaTest_Loader;


@WebServlet("/user")
public class AnonymousServlet extends BaseServlet{
	private static final IUserDao userDao = new UserDaoImpl();
	private static  final IGoodsDao goodsDao = new GoodsDaoImpl();

	public String index(HttpServletRequest req, HttpServletResponse resp){
		String index = req.getParameter("page");
		int page = 1;
		if(index!=null){
			page = Integer.parseInt(index);
		}
		Page<Goods> pages = goodsDao.page(10, page, null);
		req.setAttribute("page",pages);
		return "index.jsp";
	}
	public String registerInput(HttpServletRequest req, HttpServletResponse resp){
		return "user/registerInput.jsp";
	}
	public String loginInput(HttpServletRequest req, HttpServletResponse resp){
		return "user/loginInput.jsp";
	}


	public String login(HttpServletRequest req, HttpServletResponse resp){
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if(username==null||password==null||"".equals(username)||"".equals(password)) {
			req.setAttribute("error", "用户名密码不能为空！");
			return "user/loginInput.jsp";
		}
		try {
			User user = userDao.login(username,password);
			req.getSession().setAttribute("user",user);
			return  "redirect:user?method=index";
		} catch (UserException e) {
			req.setAttribute("error", e.getMessage());
			return "user/loginInput.jsp";
		}
	}


	public String register(HttpServletRequest req, HttpServletResponse resp){
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		User user = new User(username,password,phone,address);
		userDao.add(user);
		return "redirect:user?method=loginInput";
	}

}

