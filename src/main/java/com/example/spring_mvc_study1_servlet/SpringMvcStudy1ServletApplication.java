package com.example.spring_mvc_study1_servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
// 현재 패키지(com.example.spring_mvc_study1_servlet) 하위에서 Servlet을 모두 찾아서 등록 -> 서블릿 자동 등록
@SpringBootApplication
public class SpringMvcStudy1ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcStudy1ServletApplication.class, args);
	}

}
