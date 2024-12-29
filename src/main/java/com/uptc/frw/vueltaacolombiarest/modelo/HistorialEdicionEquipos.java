package com.uptc.frw.vueltaacolombiarest.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uptc.frw.vueltaacolombiarest.modelo.Key.HistorialEdicionEquiposKey;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "HISTORIAL_EDICION_EQUIPO")
@IdClass(HistorialEdicionEquiposKey.class)
public class HistorialEdicionEquipos {

    @Column(name = "ID_EQUIPO",insertable = false, updatable = false)
    private long idEquipo;
    @Column(name = "ID_EDICION",insertable = false, updatable = false)
    private long idEdicion;
    @Column(name = "ID_PATROCINADOR",insertable = false, updatable = false)
    private long idPatrocinador;

    @Id
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_EQUIPO")
    private Equipo equipo;

    @Id
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_EDICION")
    private Edicion edicion;

    @Id
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_PATROCINADOR")
    private Patrocinador patrocinador;

    public long getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(long idEquipo) {
        this.idEquipo = idEquipo;
    }

    public long getIdEdicion() {
        return idEdicion;
    }

    public void setIdEdicion(long idEdicion) {
        this.idEdicion = idEdicion;
    }

    public long getIdPatrocinador() {
        return idPatrocinador;
    }

    public void setIdPatrocinador(long idPatrocinador) {
        this.idPatrocinador = idPatrocinador;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Edicion getEdicion() {
        return edicion;
    }

    public void setEdicion(Edicion edicion) {
        this.edicion = edicion;
    }

    public Patrocinador getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(Patrocinador patrocinador) {
        this.patrocinador = patrocinador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistorialEdicionEquipos that = (HistorialEdicionEquipos) o;
        return idEquipo == that.idEquipo && idEdicion == that.idEdicion && idPatrocinador == that.idPatrocinador;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEquipo, idEdicion, idPatrocinador);
    }

    @Override
    public String toString() {
        return "HistorialEdicionEquipos{" +
                "idEquipo=" + idEquipo +
                ", idEdicion=" + idEdicion +
                ", idPatrocinador=" + idPatrocinador +
                '}';
    }
}
