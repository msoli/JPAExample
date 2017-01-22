package com.example.repository;

import com.example.model.Heroe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Miguel on 22/01/2017.
 */
public interface HeroeRepository extends JpaRepository<Heroe, Integer> {

    @Query("select a from Heroe a where a.name= :name")
    Heroe findByName(@Param(value = "name") String name);
}
