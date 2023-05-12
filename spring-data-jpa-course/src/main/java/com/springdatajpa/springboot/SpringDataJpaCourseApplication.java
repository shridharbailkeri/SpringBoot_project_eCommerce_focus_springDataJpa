package com.springdatajpa.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataJpaCourseApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaCourseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// code,  first approach to execute piece of code implements CommandLineRunner  and implement methods
		// second approach write junit test to execute piece of code
	}
}
