package com.ead.authuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class AuthuserApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthuserApplication.class, args);
	}

}
