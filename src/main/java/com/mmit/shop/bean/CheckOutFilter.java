package com.mmit.shop.bean;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter({"/check-out.xhtml","/myOrder.xhtml"})
public class CheckOutFilter implements Filter {

	@Inject
	private LoginBean loginBean;
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if(loginBean.getLoginUser().getId() == 0) {
			HttpServletRequest req=(HttpServletRequest) request;
			req.getServletContext().getRequestDispatcher("/login.xhtml").forward(request, response);
			
		}else {
			chain.doFilter(request, response);
			
		}
	}

}
