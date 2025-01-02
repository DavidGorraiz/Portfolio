package com.uptc.frw.vueltaacolombiarest.model.Key;

import com.uptc.frw.vueltaacolombiarest.model.Edicion;
import com.uptc.frw.vueltaacolombiarest.model.Equipo;
import com.uptc.frw.vueltaacolombiarest.model.Patrocinador;

import java.io.Serializable;

public class HistorialEdicionEquiposKey implements Serializable {

    private Edicion edicion;
    private Equipo equipo;
    private Patrocinador patrocinador;

    public HistorialEdicionEquiposKey() {
    }

    public HistorialEdicionEquiposKey(Edicion edicion, Equipo equipo, Patrocinador patrocinador) {
        this.edicion = edicion;
        this.equipo = equipo;
        this.patrocinador = patrocinador;
    }

    public Edicion getEdicion() {
        return edicion;
    }

    public void setEdicion(Edicion edicion) {
        this.edicion = edicion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Patrocinador getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(Patrocinador patrocinador) {
        this.patrocinador = patrocinador;
    }
}
