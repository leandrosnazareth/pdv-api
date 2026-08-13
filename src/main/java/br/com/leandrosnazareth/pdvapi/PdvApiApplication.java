package br.com.leandrosnazareth.pdvapi;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.info.BuildProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.util.Optional;

@SpringBootApplication
@EnableCaching
public class PdvApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(PdvApiApplication.class, args);
	}

	@Bean
	ApplicationRunner printApplicationInfo(Environment environment, Optional<BuildProperties> buildProperties) {
		return args -> {
			String applicationName = environment.getProperty("spring.application.name", "pdv-api");
			String version = buildProperties.map(BuildProperties::getVersion).orElse("0.0.1-SNAPSHOT");
			String port = environment.getProperty("server.port", "8080");

			System.out.println();
			System.out.println("========================================");
			System.out.println(" " + applicationName + " iniciado com sucesso");
			System.out.println(" Versao: " + version);
			System.out.println(" URL: http://localhost:" + port);
			System.out.println(" Swagger: http://localhost:" + port + "/swagger-ui/");
			System.out.println("----------------------------------------");
			System.out.println(" Usuario padrao: admin");
			System.out.println(" Senha padrao: 123");
			System.out.println("========================================");
			System.out.println();
		};
	}
}
