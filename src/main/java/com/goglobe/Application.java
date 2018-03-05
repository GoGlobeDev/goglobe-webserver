package com.goglobe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.goglobe.dao.mapper")
public class Application extends SpringBootServletInitializer{
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}
