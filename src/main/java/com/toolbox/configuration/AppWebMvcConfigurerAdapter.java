package com.toolbox.configuration;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.toolbox.AppContext;



@Configuration
public class AppWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
	public final static String SESSION_KEY = "userId";

	@Bean
	public SecurityInterceptor getSecurityInterceptor() {
		return new SecurityInterceptor();
	}

	@Override
    public void addViewControllers(ViewControllerRegistry registry) {    
    }
	
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

//		addInterceptor.excludePathPatterns("/error");
//		addInterceptor.excludePathPatterns("/login**");
		addInterceptor.addPathPatterns("/**");
	}

	private class SecurityInterceptor extends HandlerInterceptorAdapter {
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws IOException {
			AppContext.setHttpSession(request.getSession());
			AppContext.setHttpServletRequest(request);
			AppContext.setHttpServletResponse(response);
			
//			String requestStr = request.getParameter("request");
//			DataObject dataObj = new DataObject(requestStr);
//			if ("user.login".equalsIgnoreCase(dataObj.getAction()))
//				return true;
//
//			// 判断是否已有该用户登录的session
//			HttpSession session = request.getSession();
//			if (session.getAttribute(SESSION_KEY) != null) {
//				return true;
//			}
//
//			// 跳转到登录页
//			dataObj.getBody().clear();
//			dataObj.setStatusCode("1000");
//			dataObj.setStatusText("用户未登陆");
//			response.getWriter().write(dataObj.toString());
//
//			return false;
			
			return true;
					 
		}
		
		@Override
		public void postHandle(
				HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
				throws Exception {
		}
	}
}
