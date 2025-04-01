package com.healCare.JournalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.core.env.AbstractEnvironment;

@SpringBootApplication
@EnableTransactionManagement
public class JournalApplication {

	public static void main(String[] args) {
		// Force Spring to load application.yml
		System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "default");
		SpringApplication.run(JournalApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager falana (MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}
}
