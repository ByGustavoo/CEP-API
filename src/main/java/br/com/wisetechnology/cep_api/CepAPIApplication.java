package br.com.wisetechnology.cep_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CepAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(CepAPIApplication.class, args);
	}
}