package com.example.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Miguel on 22/01/2017.
 */
@Entity
public class Heroe {
    private Integer idHero;
    private String name;
    private Boolean estatus;
    private String superPower;

    @Id
    @Column(name = "id_hero")
    public Integer getIdHero() {
        return idHero;
    }

    public void setIdHero(Integer idHero) {
        this.idHero = idHero;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "estatus")
    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    @Basic
    @Column(name = "super_power")
    public String getSuperPower() {
        return superPower;
    }

    public void setSuperPower(String superPower) {
        this.superPower = superPower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Heroe heroe = (Heroe) o;

        if (idHero != null ? !idHero.equals(heroe.idHero) : heroe.idHero != null) return false;
        if (name != null ? !name.equals(heroe.name) : heroe.name != null) return false;
        if (estatus != null ? !estatus.equals(heroe.estatus) : heroe.estatus != null) return false;
        if (superPower != null ? !superPower.equals(heroe.superPower) : heroe.superPower != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idHero != null ? idHero.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (estatus != null ? estatus.hashCode() : 0);
        result = 31 * result + (superPower != null ? superPower.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Heroe{" +
                "idHero=" + idHero +
                ", name='" + name + '\'' +
                ", estatus=" + estatus +
                ", superPower='" + superPower + '\'' +
                '}';
    }
}
