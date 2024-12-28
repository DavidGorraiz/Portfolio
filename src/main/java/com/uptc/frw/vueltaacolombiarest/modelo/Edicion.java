package com.uptc.frw.vueltaacolombiarest.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "EDICIONES")
public class Edicion {
    @Id
    @Column(name = "ID_EDICION")
    private long id;
    @Column(name = "FECHA_INICIO")
    private Date fecha_inicial;
    @Column(name = "FECHA_FIN")
    private Date fecha_final;

    @JsonIgnore
    @OneToMany(mappedBy = "edicion")
    private List<Etapa> etapas;

    @JsonIgnore
    @OneToMany(mappedBy = "edicion")
    private List<HistorialEdicionEquipos> historial_equipos;

    public Edicion() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFecha_inicial() {
        return fecha_inicial;
    }

    public void setFecha_inicial(Date fecha_inicial) {
        this.fecha_inicial = fecha_inicial;
    }

    public Date getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(Date fecha_final) {
        this.fecha_final = fecha_final;
    }

    public List<Etapa> getEtapas() {
        return etapas;
    }

    public void setEtapas(List<Etapa> etapas) {
        this.etapas = etapas;
    }

    public List<HistorialEdicionEquipos> getHistorial_equipos() {
        return historial_equipos;
    }

    public void setHistorial_equipos(List<HistorialEdicionEquipos> historial_equipos) {
        this.historial_equipos = historial_equipos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edicion edicion = (Edicion) o;
        return id == edicion.id && Objects.equals(fecha_inicial, edicion.fecha_inicial) && Objects.equals(fecha_final, edicion.fecha_final);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha_inicial, fecha_final);
    }

    @Override
    public String toString() {
        return "Edition{" +
                "id=" + id +
                ", fecha_inicial=" + fecha_inicial +
                ", fecha_final=" + fecha_final +
                '}';
    }
}
