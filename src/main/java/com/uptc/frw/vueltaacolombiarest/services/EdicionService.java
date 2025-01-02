package com.uptc.frw.vueltaacolombiarest.services;

import com.uptc.frw.vueltaacolombiarest.model.Edicion;
import com.uptc.frw.vueltaacolombiarest.repository.EdicionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdicionService {

    @Autowired
    private EdicionRepo edicionRepo;

    public List<Edicion> findAll(){
        return edicionRepo.findAll();
    }

    public Edicion findById(long id){
        return edicionRepo.findById(id).orElse(null);
    }

    public Edicion save(Edicion edicion){
        return edicionRepo.save(edicion);
    }

    public Edicion update(long id,Edicion edicion){
        Edicion edicionOld = edicionRepo.findById(id).orElse(null);
        if (edicionOld != null){
            edicionOld.setFecha_inicial(edicion.getFecha_inicial());
            edicionOld.setFecha_final(edicion.getFecha_final());
            return edicionRepo.save(edicionOld);
        }
        throw new RuntimeException();
    }

    public Edicion delete(long id){
        Edicion edicion = edicionRepo.findById(id).orElse(null);
        if (edicion != null){
            edicionRepo.delete(edicion);
            return edicion;
        }
        throw new RuntimeException();
    }
}
