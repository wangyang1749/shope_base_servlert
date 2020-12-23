package com.shop.servlet;

import java.io.IOException;

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

@WebServlet("/user.do")
public class UserServlet extends BaseServlet{

	private static final IUserDao userDao = new UserDaoImpl();
	private static final IGoodsDao goodsDao = new GoodsDaoImpl();

	public String info(HttpServletRequest req,HttpServletResponse resp){
		User user = (User)req.getSession().getAttribute("user");
		User findUser = userDao.findById(user.getId());
		req.setAttribute("user",findUser );
		return "user/info.jsp";
	}

	public String details(HttpServletRequest req,HttpServletResponse resp){
		String id = req.getParameter("id");
		Goods goods = goodsDao.findById(Integer.parseInt(id));
		req.setAttribute("goods",goods);
		return "user/details.jsp";
	}


	public String logout(HttpServletRequest req,HttpServletResponse resp){
		req.getSession().invalidate();
		return  "redirect:user?method=index";
	}
	public String  changePasswordInput(HttpServletRequest req,HttpServletResponse resp){
		return "user/changePasswordInput.jsp";
	}
	public String  changePassword(HttpServletRequest req,HttpServletResponse resp){
		User user = (User)req.getSession().getAttribute("user");
		int id  = user.getId();
		String  beforePassword=req.getParameter("beforePassword");
		String  afterPassword=req.getParameter("afterPassword");
		User findUser =userDao.findById(id);

		if(!beforePassword.equals(findUser.getPassword())) {
			req.setAttribute("error", "与之前密不一致！");
			return "error.jsp";
		}
		findUser.setPassword(afterPassword);
		userDao.update(id, findUser);

		return "redirect:user.do?method=info";
	}

}
