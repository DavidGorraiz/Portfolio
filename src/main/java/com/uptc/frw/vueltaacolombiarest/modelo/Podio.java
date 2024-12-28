package com.uptc.frw.vueltaacolombiarest.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "PODIOS")
public class Podio {
    @Id
    @Column(name = "ID_PODIO")
    private long id;
    @Column(name = "ID_ETAPA", insertable = false, updatable = false)
    private long idEtapa;
    @Column(name = "ID_CORREDOR", insertable = false, updatable = false)
    private long idCorredor;
    @Column(name = "POSICION")
    private long posicion;
    @Column(name = "TIEMPO")
    private Timestamp tiempo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_ETAPA")
    private Etapa etapa;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_CORREDOR")
    private Corredor corredor;

    public Podio() {
    }

    public Podio(long id, long posicion, Timestamp tiempo) {
        this.id = id;
        this.posicion = posicion;
        this.tiempo = tiempo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPosicion() {
        return posicion;
    }

    public void setPosicion(long posicion) {
        this.posicion = posicion;
    }

    public Timestamp getTiempo() {
        return tiempo;
    }

    public void setTiempo(Timestamp tiempo) {
        this.tiempo = tiempo;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    public Corredor getCorredor() {
        return corredor;
    }

    public void setCorredor(Corredor corredor) {
        this.corredor = corredor;
    }

    public long getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(long idEtapa) {
        this.idEtapa = idEtapa;
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
        Podio podio = (Podio) o;
        return id == podio.id && posicion == podio.posicion && Objects.equals(tiempo, podio.tiempo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, posicion, tiempo);
    }

    @Override
    public String toString() {
        return "Podio{" +
                "id=" + id +
                ", posicion=" + posicion +
                ", tiempo=" + tiempo +
                '}';
    }
}
