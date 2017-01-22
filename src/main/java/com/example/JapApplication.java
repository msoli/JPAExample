package com.example;

import com.example.repository.HeroeRepository;
import org.hibernate.service.spi.InjectService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.inject.Inject;

@SpringBootApplication
public class JapApplication implements CommandLineRunner  {

	public static void main(String[] args) {
		SpringApplication.run(JapApplication.class, args);
	}


	@Inject
	private HeroeRepository heroeRepository;

	@Override
	public void run(String... args) throws Exception {

		System.out.println(heroeRepository.findByName("Logan"));

		System.out.println("-----------");

		heroeRepository.findAll().stream().forEach(heroe -> {
			System.out.println(heroe.toString());
		});
	}
}
