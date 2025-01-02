package com.uptc.frw.vueltaacolombiarest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CORREDORES")
public class Corredor {
    @Id
    @Column(name = "ID_CORREDOR")
    private long id;
    @Column(name = "NOMBRE_CORREDOR")
    private String nombre;
    @Column(name = "APELLIDOS_CORREDOR")
    private String apellido;
    @Column(name = "FECHA_NACIMIENTO_CORREDOR")
    private Date fechaNacimiento;
    @Column(name = "ID_PAIS", insertable = false, updatable = false)
    private long idPais;

    @ManyToOne
    @JoinColumn(name = "ID_PAIS")
    private Pais pais;

    @JsonIgnore
    @OneToMany(mappedBy = "corredor")
    private List<Podio> podios;

    @JsonIgnore
    @OneToMany(mappedBy = "corredor")
    private List<HistorialEquipos> historialEquipos;

    public Corredor() {
    }

    public Corredor(long id, String nombre, String apellido, Date fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<Podio> getPodios() {
        return podios;
    }

    public void setPodios(List<Podio> podios) {
        this.podios = podios;
    }

    public List<HistorialEquipos> getHistorialEquipos() {
        return historialEquipos;
    }

    public void setHistorialEquipos(List<HistorialEquipos> historialEquipos) {
        this.historialEquipos = historialEquipos;
    }

    public long getIdPais() {
        return idPais;
    }

    public void setIdPais(long idPais) {
        this.idPais = idPais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Corredor corredor = (Corredor) o;
        return id == corredor.id && Objects.equals(nombre, corredor.nombre) && Objects.equals(apellido, corredor.apellido) && Objects.equals(fechaNacimiento, corredor.fechaNacimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, fechaNacimiento);
    }

    @Override
    public String toString() {
        return "Corredor{" +
                "fechaNacimiento=" + fechaNacimiento +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", id=" + id +
                '}';
    }
}
