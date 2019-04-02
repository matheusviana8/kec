package com.kec.comercial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.kec.comercial.config.property.KecApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(KecApiProperty.class)
public class KecApplication {

	public static void main(String[] args) {
		SpringApplication.run(KecApplication.class, args);
	}

	
}
