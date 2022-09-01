package br.com.leandrosnazareth.pdvapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableCaching
public class PdvApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(PdvApiApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}
}