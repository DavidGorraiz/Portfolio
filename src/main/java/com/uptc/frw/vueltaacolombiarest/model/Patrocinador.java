package com.uptc.frw.vueltaacolombiarest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "PATROCINADORES")
public class Patrocinador {
    @Id
    @Column(name = "ID_PATROCINADOR")
    private long id;
    @Column(name = "NIT_PATROCINADOR")
    private String NIT;
    @Column(name = "NOMBRE_PATROCINADOR")
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "patrocinador")
    private List<HistorialEdicionEquipos> historialEdicionEquipos;

    public Patrocinador() {
    }

    public Patrocinador(long id, String NIT, String nombre) {
        this.id = id;
        this.NIT = NIT;
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<HistorialEdicionEquipos> getHistorailEdicionEquipos() {
        return historialEdicionEquipos;
    }

    public void setHistorailEdicionEquipos(List<HistorialEdicionEquipos> historialEdicionEquipos) {
        this.historialEdicionEquipos = historialEdicionEquipos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patrocinador that = (Patrocinador) o;
        return id == that.id && Objects.equals(NIT, that.NIT) && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, NIT, nombre);
    }

    @Override
    public String toString() {
        return "Patrocinador{" +
                "id=" + id +
                ", NIT='" + NIT + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
