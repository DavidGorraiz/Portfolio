package com.uptc.frw.vueltaacolombiarest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "EQUIPOS")
public class Equipo {
    @Id
    @Column(name = "ID_EQUIPO")
    private long id;
    @Column(name = "NOMBRE_EQUIPO")
    private String nombre;
    @Column(name = "FECHA_FUNDACION")
    private Date fechaFundacion;

    @JsonIgnore
    @OneToMany(mappedBy = "equipo")
    private List<HistorialEquipos> historialEquipos;

    @JsonIgnore
    @OneToMany(mappedBy = "equipo")
    private List<HistorialEdicionEquipos> historialEdicionEquipos;

    public Equipo() {
    }

    public Equipo(long id, String nombre, Date fechaFundacion) {
        this.id = id;
        this.nombre = nombre;
        this.fechaFundacion = fechaFundacion;
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

    public Date getFechaFundacion() {
        return fechaFundacion;
    }

    public void setFechaFundacion(Date fechaFundacion) {
        this.fechaFundacion = fechaFundacion;
    }

    public List<HistorialEquipos> getHistorialEquipos() {
        return historialEquipos;
    }

    public void setHistorialEquipos(List<HistorialEquipos> historialEquipos) {
        this.historialEquipos = historialEquipos;
    }

    public List<HistorialEdicionEquipos> getHistorialEdicionEquipos() {
        return historialEdicionEquipos;
    }

    public void setHistorialEdicionEquipos(List<HistorialEdicionEquipos> historialEdicionEquipos) {
        this.historialEdicionEquipos = historialEdicionEquipos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return id == equipo.id && Objects.equals(nombre, equipo.nombre) && Objects.equals(fechaFundacion, equipo.fechaFundacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, fechaFundacion);
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaFundacion=" + fechaFundacion +
                '}';
    }
}
