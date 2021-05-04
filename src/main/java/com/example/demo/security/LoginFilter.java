package com.example.demo.security;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.controller.HomeController;
import com.example.demo.entity.User;

@Component
public class LoginFilter implements Filter {

	public LoginFilter() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		if (req.getRequestURI().contains("login")) {
			chain.doFilter(request, response);
			return;
		}

		User user = (User) req.getSession().getAttribute("user");
		if (user != null) {
			chain.doFilter(request, response);
			return;
		} else {
			res.sendRedirect(HomeController.url + "/login");
		}
		chain.doFilter(request, response);

		System.out.println(req.getRequestURI());
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
