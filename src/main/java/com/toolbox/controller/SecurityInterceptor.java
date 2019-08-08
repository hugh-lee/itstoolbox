package com.toolbox.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SecurityInterceptor extends HandlerInterceptorAdapter {
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws IOException {
//		HttpSession session = request.getSession();
//
//		// 判断是否已有该用户登录的session
//		if (session.getAttribute("") != null) {
//			return true;
//		}
//
//		// 跳转到登录页
//		String url = "/login";
//		response.sendRedirect(url);
//		return false;
//
//	}
}
