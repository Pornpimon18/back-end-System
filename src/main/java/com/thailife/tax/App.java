package com.thailife.tax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.thailife.tax.service.MenuManageService;
import com.thailife.tax.service.MenuService;
import com.thailife.tax.utils.IdGenerator;



@SpringBootApplication
//@EnableJpaRepositories({"com.thailife.tax.repository"})
//@ComponentScan({"com.thailife.tax.service","com.thailife.tax.controller","com.thailife.tax.config"})
//@EntityScan("com.thailife.tax.entity")
public class App 
{
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }
	
    public static void main( String[] args )
    {
    	
    	SpringApplication.run(App.class, args);
    }
}
