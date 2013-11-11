package com.audit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
/**
 * 异常处理
 * @author dengyong
 *
 */
public class ExceptioneController implements HandlerExceptionResolver {
	
	
	Logger logger = Logger.getLogger(ExceptioneController.class.getName());
	    @Override  
	    public ModelAndView resolveException(HttpServletRequest request,   
	            HttpServletResponse response, Object handler, Exception ex) {
			     logger.warn(handler, ex);
	        return new ModelAndView("exception");   
	    }   

}
