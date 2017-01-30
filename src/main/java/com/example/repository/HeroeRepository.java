package com.example.repository;

import com.example.model.Heroe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Miguel on 22/01/2017.
 */
public interface HeroeRepository extends JpaRepository<Heroe, Integer> {

    @Query( value = "select a, b   from Heroe a LEFT JOIN a.categoriaByIdCategoria b where a.name= ?1")
    Heroe findByNameConCategoria(String name);




}
