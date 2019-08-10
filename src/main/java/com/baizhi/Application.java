package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.baizhi.dao")
public class Application {

	public static void main(String[] args) {
		System.out.println();

		SpringApplication.run(Application.class, args);
	}

}
