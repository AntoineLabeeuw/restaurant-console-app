package dev.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import dev.ihm.Menu;
import dev.ihm.options.IOptionMenu;
import dev.ihm.options.OptionListerPlats;
import dev.ihm.options.OptionTerminer;
import dev.service.IPlatService;

/**
 * Classe de configuration Java-Spring
 * 
 * @author antoinelabeeuw
 *
 */
@Configuration
@ComponentScan("dev")
@PropertySource("app.properties")
public class AppConfig {

	@Bean
	public Scanner scanner() {
		return new Scanner(System.in);
	}
}
