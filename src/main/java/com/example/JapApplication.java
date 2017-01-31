package com.example;

import com.example.config.AngularProperties;
import com.example.dao.UsuarioDAO;
import com.example.model.Heroe;
import com.example.modelv2.Usuario;
import com.example.repository.CategoriaRepository;
import com.example.repository.HeroeRepository;
import com.example.repositoryv2.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties({AngularProperties.class})
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
//        HibernateJpaAutoConfiguration.class
})
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

    @Inject
    private UsuarioDAO heroeDAO;


    @Override
    public void run(String... args) throws Exception {



        Heroe h = heroeRepository.findByNameConCategoria("Wolverine");
        System.out.println(h);

        List<Heroe> c = categoriaRepository.findByIdCategoria(2);


        c.parallelStream().forEach(System.out::println);

        List<Usuario> listUsers = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            listUsers.add(new Usuario(new Date().getTime() + ""));
        }

        System.out.println("Tamaño: "+listUsers.size());
        heroeDAO.create(listUsers);
        System.out.println("Finish batch insetr");

        usuarioRepository.findAll().parallelStream().forEach(System.out::println);

        System.out.println("-----------");

        heroeRepository.findAll().stream().forEach(System.out::println);


    }


}
