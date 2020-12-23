package com.shop.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("method");
        if (name==null||"".equals(name)){
            name="index";
        }
        //如果是以redirect开头则重定向
        String redirect = "redirect:";
        try {
            //根据参数获取当前对象需要执行的函数
            Method m = null;
            try {
                m = this.getClass().getMethod(name, HttpServletRequest.class, HttpServletResponse.class);
            } catch (NoSuchMethodException e) {
                req.setAttribute("error",e.getMessage());
                req.getRequestDispatcher("WEB-INF/error.jsp").forward(req, resp);
                return;
            }
            //使用当前对象执行该函数
            String path= (String)m.invoke(this, req,resp);
            //根据返回值跳转到相应的jsp页面
            if(path==null){
                return;
            }else if(path.startsWith(redirect)){
                resp.sendRedirect(path.substring(redirect.length()));
            }else{
                req.getRequestDispatcher("WEB-INF/"+path).forward(req, resp);
            }

        }catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
