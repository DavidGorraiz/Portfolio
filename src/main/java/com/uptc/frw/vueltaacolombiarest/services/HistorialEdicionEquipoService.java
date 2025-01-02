package com.uptc.frw.vueltaacolombiarest.services;

import com.uptc.frw.vueltaacolombiarest.model.Edicion;
import com.uptc.frw.vueltaacolombiarest.model.Equipo;
import com.uptc.frw.vueltaacolombiarest.model.HistorialEdicionEquipos;
import com.uptc.frw.vueltaacolombiarest.model.Key.HistorialEdicionEquiposKey;
import com.uptc.frw.vueltaacolombiarest.model.Patrocinador;
import com.uptc.frw.vueltaacolombiarest.repository.EdicionRepo;
import com.uptc.frw.vueltaacolombiarest.repository.EquipoRepo;
import com.uptc.frw.vueltaacolombiarest.repository.HistorialEdicionEquipoRepo;
import com.uptc.frw.vueltaacolombiarest.repository.PatrocinadorRepo;
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

    public HistorialEdicionEquipos delete(long idEquipo, long idEdicion, long idPatrocinador){
        HistorialEdicionEquipos historialEdicionEquipos = findById(idEquipo,idEdicion,idPatrocinador);
        System.out.println(historialEdicionEquipos);
        if(historialEdicionEquipos != null){
            historialEdicionEquipoRepo.delete(historialEdicionEquipos);
            return historialEdicionEquipos;
        }
        throw new RuntimeException();
    }
}
