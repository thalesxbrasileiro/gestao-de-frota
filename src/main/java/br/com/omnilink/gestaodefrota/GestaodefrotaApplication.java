package br.com.omnilink.gestaodefrota;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GestaodefrotaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaodefrotaApplication.class, args);
	}

}
