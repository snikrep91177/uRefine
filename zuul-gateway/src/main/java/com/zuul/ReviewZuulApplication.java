package com.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@CrossOrigin("http://localhost:4200")
public class ReviewZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewZuulApplication.class, args);
	}

}
