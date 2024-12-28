package com.uptc.frw.vueltaacolombiarest.servicios;

import com.uptc.frw.vueltaacolombiarest.modelo.Corredor;
import com.uptc.frw.vueltaacolombiarest.modelo.Equipo;
import com.uptc.frw.vueltaacolombiarest.modelo.HistorialEquipos;
import com.uptc.frw.vueltaacolombiarest.repositorio.CorredorRepo;
import com.uptc.frw.vueltaacolombiarest.repositorio.EquipoRepo;
import com.uptc.frw.vueltaacolombiarest.repositorio.HistorialEquiposRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class HistorialEquiposService {

    @Autowired
    private HistorialEquiposRepo historialEquiposRepo;

    @Autowired
    private EquipoRepo equipoRepo;

    @Autowired
    private CorredorRepo corredorRepo;

    public List<HistorialEquipos> findAll() {
        return historialEquiposRepo.findAll();
    }

    public HistorialEquipos findById(long id) {
        return historialEquiposRepo.findById(id).orElse(null);
    }

    public HistorialEquipos save(HistorialEquipos historialEquipos) {
        Equipo equipo = equipoRepo.findById(historialEquipos.getIdEquipo()).orElse(null);
        Corredor corredor = corredorRepo.findById(historialEquipos.getIdCorredor()).orElse(null);
        historialEquipos.setEquipo(equipo);
        historialEquipos.setCorredor(corredor);
        return historialEquiposRepo.save(historialEquipos);
    }

    public HistorialEquipos update(long id, HistorialEquipos historialEquipos) {
        HistorialEquipos historialEquipoOld = findById(id);
        Equipo equipo = equipoRepo.findById(historialEquipos.getIdEquipo()).orElse(null);
        Corredor corredor= corredorRepo.findById(historialEquipos.getIdCorredor()).orElse(null);
        if(historialEquipoOld != null && equipo != null && corredor != null) {
            historialEquipoOld.setEquipo(equipo);
            historialEquipoOld.setCorredor(corredor);
            historialEquipoOld.setAnioVinculacion(historialEquipos.getAnioVinculacion());
            return historialEquiposRepo.save(historialEquipoOld);
        }
        throw new RuntimeException();
    }

    public HistorialEquipos delete(long id) {
        HistorialEquipos historialEquipoOld = findById(id);
        if(historialEquipoOld != null) {
            historialEquiposRepo.delete(historialEquipoOld);
            return historialEquipoOld;
        }
        throw new RuntimeException();
    }
}
