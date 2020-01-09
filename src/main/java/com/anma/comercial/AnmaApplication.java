package com.anma.comercial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.anma.comercial.config.property.AnmaApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(AnmaApiProperty.class)
public class AnmaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnmaApplication.class, args);
	}

	
}
