package com.thailife.tax.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class InterceptorAppConfig extends WebMvcConfigurerAdapter {
   @Autowired
   InterceptorConfig interceptorConfig;
   
   @Override
	 public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	    configurer.favorPathExtension(false).favorParameter(true);
	 }
	
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/**").addResourceLocations("/");
  }

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
	   registry.addInterceptor(interceptorConfig).addPathPatterns("/**");
   }
}