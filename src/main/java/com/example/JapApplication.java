package com.example;

import com.example.config.AngularProperties;
import com.example.model.Categoria;
import com.example.model.Heroe;
import com.example.modelv2.Usuario;
import com.example.repository.CategoriaRepository;
import com.example.repository.HeroeRepository;
import com.example.repositoryv2.UsuarioRepository;
import org.hibernate.service.spi.InjectService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties({AngularProperties.class})
@EnableAutoConfiguration(exclude = {  DataSourceAutoConfiguration.class })
public class JapApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JapApplication.class, args);
    }


    @Inject
    private HeroeRepository heroeRepository;

    @Inject
    private CategoriaRepository categoriaRepository;

    @Inject
    private UsuarioRepository usuarioRepository;


    @Override
    public void run(String... args) throws Exception {

        Heroe h = heroeRepository.findByNameConCategoria("Wolverine");

        List<Heroe> c = categoriaRepository.findByIdCategoria(2);

        usuarioRepository.findAll().stream().forEach(System.out::println);

        System.out.println("-----------");

        heroeRepository.findAll().stream().forEach(System.out::println);


    }


}
