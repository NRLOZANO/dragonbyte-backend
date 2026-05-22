package com.grupo7.cursosdragonbyte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CursosdragonbyteApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursosdragonbyteApplication.class, args);
	}

}
