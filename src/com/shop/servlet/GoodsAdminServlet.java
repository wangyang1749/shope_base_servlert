package com.shop.servlet;

import com.shop.dao.GoodsDaoImpl;
import com.shop.dao.IGoodsDao;
import com.shop.model.Goods;
import com.shop.model.Page;
import com.shop.model.User;
import com.shop.utils.ParamUtil;
import com.shop.utils.PropertiesUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

@WebServlet("/goods.admin")
public class GoodsAdminServlet extends  BaseServlet {

    private  static IGoodsDao goodsDao = new GoodsDaoImpl();

    public String addInput(HttpServletRequest request, HttpServletResponse response){
        return "goods/addInput.jsp";
    }
    public String updateInput(HttpServletRequest request, HttpServletResponse response){
        
        String parameter = request.getParameter("id");
        Goods goods = goodsDao.findById(Integer.parseInt(parameter));
        request.setAttribute("goods",goods);
        return "goods/updateInput.jsp";
    }

    public String add(HttpServletRequest request, HttpServletResponse response){
    	request = new MultipartRequestWrapper(request);
        Goods goods = new Goods();
        ParamUtil.get(goods,request);
        goodsDao.add(goods);
        return "redirect:goods.admin?method=page";
    }

    public String update(HttpServletRequest request, HttpServletResponse response){
        request = new MultipartRequestWrapper(request);
        String parameter = request.getParameter("id");
        Goods goods = new Goods();
        ParamUtil.get(goods,request);
        goodsDao.update(Integer.parseInt(parameter),goods);
        return "redirect:goods.admin?method=page";
    }
    public String delete(HttpServletRequest request, HttpServletResponse response){
        String parameter = request.getParameter("id");
        goodsDao.delete(Integer.parseInt(parameter));
        return "redirect:goods.admin?method=page";
    }


    public String page(HttpServletRequest request, HttpServletResponse response){
        String index = request.getParameter("page");
        int page = 1;
        if(index!=null){
          page = Integer.parseInt(index);
        }
        Page<Goods> pages = goodsDao.page(10, page, null);
        request.setAttribute("page",pages);
        return "goods/goodsPage.jsp";
    }

}
