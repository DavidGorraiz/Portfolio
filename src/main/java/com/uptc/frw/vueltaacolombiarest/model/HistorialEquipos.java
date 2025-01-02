package com.uptc.frw.vueltaacolombiarest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "HISTORIAL_EQUIPOS")
public class HistorialEquipos {
    @Id
    @Column(name = "ID_HISTORIAL")
    private long id;
    @Column(name = "ID_EQUIPO",insertable = false, updatable = false)
    private long idEquipo;
    @Column(name = "ID_CORREDOR",insertable = false, updatable = false)
    private long idCorredor;
    @Column(name = "ANIO_VINCULACION")
    private long anioVinculacion;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_EQUIPO")
    private Equipo equipo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_CORREDOR")
    private Corredor corredor;

    public HistorialEquipos() {
    }

    public HistorialEquipos(long id, long anioVinculacion) {
        this.id = id;
        this.anioVinculacion = anioVinculacion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAnioVinculacion() {
        return anioVinculacion;
    }

    public void setAnioVinculacion(long anioVinculacion) {
        this.anioVinculacion = anioVinculacion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Corredor getCorredor() {
        return corredor;
    }

    public void setCorredor(Corredor corredor) {
        this.corredor = corredor;
    }

    public long getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(long idEquipo) {
        this.idEquipo = idEquipo;
    }

    public long getIdCorredor() {
        return idCorredor;
    }

    public void setIdCorredor(long idCorredor) {
        this.idCorredor = idCorredor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistorialEquipos that = (HistorialEquipos) o;
        return id == that.id && anioVinculacion == that.anioVinculacion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, anioVinculacion);
    }

    @Override
    public String toString() {
        return "HistorialEquipos{" +
                "anioVinculacion=" + anioVinculacion +
                ", id=" + id +
                '}';
    }
}
