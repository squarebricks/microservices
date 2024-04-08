package com.popcorn.college;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CollegesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollegesApplication.class, args);
	}

}
