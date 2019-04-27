package com.company.sumpla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.company.sumpla.repositories")
@EntityScan("com.company.sumpla.model")
public class SumplaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SumplaApplication.class, args);
	}

}
