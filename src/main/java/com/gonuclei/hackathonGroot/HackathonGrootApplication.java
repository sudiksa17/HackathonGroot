package com.gonuclei.hackathonGroot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@EnableAutoConfiguration()
@Component
@SpringBootApplication
public class HackathonGrootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackathonGrootApplication.class, args);
	}

}
