package dev.config;

import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuration Java-Spring
 * 
 * @author antoinelabeeuw
 *
 */
@Configuration
@ComponentScan("dev.ihm")
@ComponentScan("dev.service")
@ComponentScan("dev.dao")
public class AppConfig {

	@Bean
	public Scanner scanner() {
		return new Scanner(System.in);
	}
}
