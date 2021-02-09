package com.trustly.emiliano;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * The entry point of the Spring Boot application.
 *
 * @author Emiliano Pessôa
 *
 */
@SpringBootApplication
public class Application {

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

}
