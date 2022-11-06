package com.status.pokemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.status.pokemon"})
public class PokemonApplication {
	public static void main(String[] args) {
		SpringApplication.run(PokemonApplication.class, args);
	}

}
