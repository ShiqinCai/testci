package com.netease.cloud.nce.ctrl.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class AppsExceptionResolver implements HandlerExceptionResolver, Ordered {

	private static final Logger logger = Logger.getLogger(AppsExceptionResolver.class);

	public int getOrder() {
		return Integer.MIN_VALUE;
	}

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		try {
			response.sendRedirect("/error?code=500");
		} catch (IOException ignore) {
			logger.debug(ignore.getMessage());
		}
		return new ModelAndView();
	}
}
