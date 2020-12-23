package com.shop.filter;

import com.shop.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(value = "*.admin")
public class AdminAuthorizeFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		Object obj = request.getSession().getAttribute("user");
		if(obj==null){
			response.sendRedirect(request.getContextPath()+"/user?method=loginInput");
			return;
		}
		User user= (User)obj;
		if(user.getType()!=1){
			response.sendRedirect(request.getContextPath()+"/user");
			return;
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	
	
}
