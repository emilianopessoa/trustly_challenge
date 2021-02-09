package com.trustly.emiliano;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 
 * The entry point of the Spring Boot application.
 *
 * @author Emiliano Pessôa
 *
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	/**
	 * Application Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SpringApplication.run(Application.class, args);
		} catch (Throwable e) {
			if (!e.getClass().getName().contains("SilentExitException")) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

}
