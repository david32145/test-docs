package br.ufc.crateus.testdocs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class TestdocsApplication {
	public static void main(String[] args) {
		SpringApplication.run(TestdocsApplication.class, args);
	}
}
