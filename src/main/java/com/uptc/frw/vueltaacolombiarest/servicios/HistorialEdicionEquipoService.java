package com.uptc.frw.vueltaacolombiarest.servicios;

import com.uptc.frw.vueltaacolombiarest.modelo.Edicion;
import com.uptc.frw.vueltaacolombiarest.modelo.Equipo;
import com.uptc.frw.vueltaacolombiarest.modelo.HistorialEdicionEquipos;
import com.uptc.frw.vueltaacolombiarest.modelo.Key.HistorialEdicionEquiposKey;
import com.uptc.frw.vueltaacolombiarest.modelo.Patrocinador;
import com.uptc.frw.vueltaacolombiarest.repositorio.EdicionRepo;
import com.uptc.frw.vueltaacolombiarest.repositorio.EquipoRepo;
import com.uptc.frw.vueltaacolombiarest.repositorio.HistorialEdicionEquipoRepo;
import com.uptc.frw.vueltaacolombiarest.repositorio.PatrocinadorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialEdicionEquipoService {

    @Autowired
    private HistorialEdicionEquipoRepo historialEdicionEquipoRepo;

    @Autowired
    private EquipoRepo equipoRepo;

    @Autowired
    private EdicionRepo edicionRepo;

    @Autowired
    private PatrocinadorRepo patrocinadorRepo;

    public List<HistorialEdicionEquipos> findAll(){
        return historialEdicionEquipoRepo.findAll();
    }

    public HistorialEdicionEquipos findById(long idEquipo, long idEdicion, long idPatrocinador){
        Equipo equipo = equipoRepo.findById(idEquipo).orElse(null);
        Edicion edicion = edicionRepo.findById(idEdicion).orElse(null);
        Patrocinador patrocinador = patrocinadorRepo.findById(idPatrocinador).orElse(null);
        HistorialEdicionEquiposKey historialEdicionEquiposKey = new HistorialEdicionEquiposKey(edicion, equipo,patrocinador);
        return historialEdicionEquipoRepo.findById(historialEdicionEquiposKey).orElse(null);
    }

    public HistorialEdicionEquipos save(HistorialEdicionEquipos historialEdicionEquipos){
        Equipo equipo = equipoRepo.findById(historialEdicionEquipos.getIdEquipo()).orElse(null);
        Edicion edicion = edicionRepo.findById(historialEdicionEquipos.getIdEdicion()).orElse(null);
        Patrocinador patrocinador = patrocinadorRepo.findById(historialEdicionEquipos.getIdPatrocinador()).orElse(null);
        historialEdicionEquipos.setEquipo(equipo);
        historialEdicionEquipos.setEdicion(edicion);
        historialEdicionEquipos.setPatrocinador(patrocinador);
        return historialEdicionEquipoRepo.save(historialEdicionEquipos);
    }
}
