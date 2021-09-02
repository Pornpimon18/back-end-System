package com.thailife.tax;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
public class App extends SpringBootServletInitializer 
{
	  public static void main( String[] args )
	    {
	    	SpringApplication.run(App.class, args);
	    	System.out.println("Version 1.0.0");
	    }
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }
	
  
}
