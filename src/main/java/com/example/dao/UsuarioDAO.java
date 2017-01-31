package com.example.dao;

import com.example.model.Heroe;
import com.example.modelv2.Usuario;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SISTEMAS03-PC on 06/01/2017.
 */
@Transactional(readOnly = true)
public interface UsuarioDAO {


    void create(List<Usuario> item);



}
