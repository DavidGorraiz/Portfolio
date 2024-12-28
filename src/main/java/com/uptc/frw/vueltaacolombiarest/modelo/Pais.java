package com.uptc.frw.vueltaacolombiarest.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "PAISES")
public class Pais {
    @Id
    @Column(name = "ID_PAIS")
    private long id;
    @Column(name = "NOMBRE_PAIS")
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "pais")
    private List<Corredor> corredores;

    public Pais() {
    }

    public Pais(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Corredor> getCorredores() {
        return corredores;
    }

    public void setCorredores(List<Corredor> corredores) {
        this.corredores = corredores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pais pais = (Pais) o;
        return id == pais.id && Objects.equals(nombre, pais.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @Override
    public String toString() {
        return "Pais{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
