package com.uptc.frw.vueltaacolombiarest.services;

import com.uptc.frw.vueltaacolombiarest.model.Equipo;
import com.uptc.frw.vueltaacolombiarest.repository.EquipoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepo equipoRepo;

    public List<Equipo> findAll(){
        return equipoRepo.findAll();
    }

    public Equipo findById(long id){
        return equipoRepo.findById(id).orElse(null);
    }

    public Equipo save(Equipo equipo){
        return equipoRepo.save(equipo);
    }

    public Equipo update(long id,Equipo equipo){
        Equipo equipoOld = equipoRepo.findById(id).orElse(null);
        if (equipoOld != null){
            equipoOld.setNombre(equipo.getNombre());
            equipoOld.setFechaFundacion(equipo.getFechaFundacion());
            return equipoRepo.save(equipoOld);
        }
        throw new RuntimeException();
    }

    public Equipo delete(long id){
        Equipo equipo = equipoRepo.findById(id).orElse(null);
        if (equipo != null){
            equipoRepo.delete(equipo);
            return equipo;
        }
        throw new RuntimeException();
    }
}
