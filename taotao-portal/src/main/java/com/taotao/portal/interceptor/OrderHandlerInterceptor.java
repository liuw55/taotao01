package com.taotao.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.manager.pojo.User;
import com.taotao.portal.util.CookieUtils;
import com.taotao.sso.service.UserService;

public class OrderHandlerInterceptor implements HandlerInterceptor {

	@Value("${TT_TICKET}")
	private String TT_TICKET;

	@Autowired
	private UserService userService;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String ticket = CookieUtils.getCookieValue(request, TT_TICKET);
		if (StringUtils.isBlank(ticket)) {
			String redirectURL = request.getRequestURL().toString();
			response.sendRedirect("/page/login.html?redirectURL=" + redirectURL);
			return false;
		}

		User user = userService.queryUserByTicket(ticket);
		if (user == null) {
			response.sendRedirect("/page/login.html");
			return false;
		}

		request.setAttribute("user", user);
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
