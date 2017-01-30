package com.example.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Miguel on 28/01/2017.
 */
@Entity
public class Categoria {
    private Integer idCategoria;
    private String descripcion;
    private Collection<Heroe> heroesByIdCategoria;

    @Id
    @Column(name = "id_categoria")
    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Basic
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria() {
    }

    public Categoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Categoria categoria = (Categoria) o;

        if (idCategoria != null ? !idCategoria.equals(categoria.idCategoria) : categoria.idCategoria != null)
            return false;
        if (descripcion != null ? !descripcion.equals(categoria.descripcion) : categoria.descripcion != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCategoria != null ? idCategoria.hashCode() : 0;
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "categoriaByIdCategoria")
    public Collection<Heroe> getHeroesByIdCategoria() {
        return heroesByIdCategoria;
    }

    public void setHeroesByIdCategoria(Collection<Heroe> heroesByIdCategoria) {
        this.heroesByIdCategoria = heroesByIdCategoria;
    }
}
