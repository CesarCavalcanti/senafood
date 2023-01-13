package br.com.senafood;

import br.com.senafood.infrastructure.impl.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class SenafoodfoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SenafoodfoodApiApplication.class, args);
	}

}
