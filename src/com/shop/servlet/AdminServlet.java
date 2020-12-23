package com.shop.servlet;

import com.shop.dao.IUserDao;
import com.shop.dao.UserDaoImpl;
import com.shop.model.Goods;
import com.shop.model.Page;
import com.shop.model.User;
import com.shop.utils.ParamUtil;
import com.shop.utils.UserException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user.admin")
public class AdminServlet extends BaseServlet{

    IUserDao userDao = new UserDaoImpl();

    public String updateInput(HttpServletRequest request, HttpServletResponse response){

        String parameter = request.getParameter("id");
        User user = userDao.findById(Integer.parseInt(parameter));
        request.setAttribute("user",user);
        return "user/updateInput.jsp";
    }

    public String update(HttpServletRequest request, HttpServletResponse response){
        request = new MultipartRequestWrapper(request);
        String parameter = request.getParameter("id");
        User user = new User();
        ParamUtil.get(user,request);
        userDao.update(Integer.parseInt(parameter),user);
        return "redirect:user.admin?method=page";
    }
    public String delete(HttpServletRequest request, HttpServletResponse response){
        String parameter = request.getParameter("id");
        int id = Integer.parseInt(parameter);
        User user = userDao.findById(id);
        if(user.getUsername().equals("admin")){
            throw new UserException("不能删除超级管理员！！");
        }
        userDao.delete(id);
        return "redirect:user.admin?method=page";
    }

    public String page(HttpServletRequest request, HttpServletResponse response){
        String index = request.getParameter("page");
        int page = 1;
        if(index!=null){
            page = Integer.parseInt(index);
        }
        Page<User> pages = userDao.page(10, page, null);
        request.setAttribute("page",pages);
        return "user/userPage.jsp";
    }
}
