package com.example.bikeweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// @Configuration
// @EnableAutoConfiguration
// @ComponentScan           三个整合成以下
@SpringBootApplication(scanBasePackages = "com.example.bikeweb")
@EnableTransactionManagement //回滚
public class BikeWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BikeWebApplication.class, args);
	}

}
