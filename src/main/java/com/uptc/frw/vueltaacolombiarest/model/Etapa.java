package com.uptc.frw.vueltaacolombiarest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ETAPAS")
public class Etapa {
    @Id
    @Column(name = "ID_ETAPA")
    private long id;
    @Column(name = "ID_EDICION", insertable = false, updatable = false)
    private long idEdicion;
    @Column(name = "ORIGEN")
    private String Origen;
    @Column(name = "DESTINO")
    private String Destino;
    @Column(name = "LONGITUD")
    private float longitud;
    @Column(name = "TIPO_ETAPA")
    private String tipoEtapa;

    @ManyToOne
    @JoinColumn(name = "ID_EDICION")
    private Edicion edicion;

    @JsonIgnore
    @OneToMany(mappedBy = "etapa")
    private List<Podio> podios;


    public Etapa() {
    }

    public Etapa(String origen, String destino, long id, float longitud, String tipoEtapa) {
        Origen = origen;
        Destino = destino;
        this.id = id;
        this.longitud = longitud;
        this.tipoEtapa = tipoEtapa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrigen() {
        return Origen;
    }

    public void setOrigen(String origen) {
        Origen = origen;
    }

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String destino) {
        Destino = destino;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public String getTipoEtapa() {
        return tipoEtapa;
    }

    public void setTipoEtapa(String tipoEtapa) {
        this.tipoEtapa = tipoEtapa;
    }

    public Edicion getEdicion() {
        return edicion;
    }

    public void setEdicion(Edicion edicion) {
        this.edicion = edicion;
    }

    public List<Podio> getPodios() {
        return podios;
    }

    public void setPodios(List<Podio> podios) {
        this.podios = podios;
    }

    public long getIdEdicion() {
        return idEdicion;
    }

    public void setIdEdicion(long idEdicion) {
        this.idEdicion = idEdicion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Etapa etapa = (Etapa) o;
        return id == etapa.id && Float.compare(longitud, etapa.longitud) == 0 && Objects.equals(Origen, etapa.Origen) && Objects.equals(Destino, etapa.Destino) && Objects.equals(tipoEtapa, etapa.tipoEtapa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Origen, Destino, longitud, tipoEtapa);
    }

    @Override
    public String toString() {
        return "Etapa{" +
                "id=" + id +
                ", Origen='" + Origen + '\'' +
                ", Destino='" + Destino + '\'' +
                ", longitud=" + longitud +
                ", tipoEtapa='" + tipoEtapa + '\'' +
                '}';
    }
}
