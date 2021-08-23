package com.thailife.tax.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class InterceptorConfig implements HandlerInterceptor {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
   @Override
   public boolean preHandle
      (HttpServletRequest request, HttpServletResponse response, Object handler) 
      throws Exception {
	   String sessionID = "";
		
		if(request.getSession(false) == null){
			sessionID = request.getSession(true).getId();
		}else{
			sessionID = request.getSession(false).getId();
		}
       logger.info("session : "+sessionID);
      System.out.println("Pre Handle method is Calling");
      return true;
   }
   @Override
   public void postHandle(HttpServletRequest request, HttpServletResponse response, 
      Object handler, ModelAndView modelAndView) throws Exception {
      
      System.out.println("Post Handle method is Calling");
   }
   @Override
   public void afterCompletion
      (HttpServletRequest request, HttpServletResponse response, Object 
      handler, Exception exception) throws Exception {
      
      System.out.println("Request and Response is completed");
   }
}