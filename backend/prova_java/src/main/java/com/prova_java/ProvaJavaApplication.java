package com.prova_java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class
})
@EnableCaching
public class ProvaJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProvaJavaApplication.class, args);
	}

}
