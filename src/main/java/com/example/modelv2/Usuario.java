package com.example.modelv2;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Miguel on 29/01/2017.
 */
@Entity
@Data
public class Usuario {
    private Integer id;
    private String nombre;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

//    public void setId(Integer id) {
//        this.id = id;
//    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }


    public Usuario() {
    }

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (id != null ? !id.equals(usuario.id) : usuario.id != null) return false;
        if (nombre != null ? !nombre.equals(usuario.nombre) : usuario.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }
}
