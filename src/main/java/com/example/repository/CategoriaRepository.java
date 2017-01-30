package com.example.repository;

import com.example.model.Categoria;
import com.example.model.Heroe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Miguel on 22/01/2017.
 */

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    @Query(value = "select b   from Categoria a LEFT JOIN a.heroesByIdCategoria b where a.idCategoria= ?1")
    List<Heroe> findByIdCategoria(int idCategoria);





}
