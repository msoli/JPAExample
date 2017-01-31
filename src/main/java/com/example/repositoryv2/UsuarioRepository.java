package com.example.repositoryv2;

import com.example.modelv2.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Miguel on 30/01/2017.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
