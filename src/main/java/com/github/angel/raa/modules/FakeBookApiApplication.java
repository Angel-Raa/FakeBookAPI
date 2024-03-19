package com.github.angel.raa.modules;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:env.properties")
public class FakeBookApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FakeBookApiApplication.class, args);
	}

}
