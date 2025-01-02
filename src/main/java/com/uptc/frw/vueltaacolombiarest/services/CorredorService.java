package com.uptc.frw.vueltaacolombiarest.services;

import com.uptc.frw.vueltaacolombiarest.model.Corredor;
import com.uptc.frw.vueltaacolombiarest.model.Pais;
import com.uptc.frw.vueltaacolombiarest.repository.CorredorRepo;
import com.uptc.frw.vueltaacolombiarest.repository.PaisRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorredorService {

    @Autowired
    private CorredorRepo corredorRepo;

    @Autowired
    private PaisRepo paisRepo;

    public List<Corredor> findAll() {
        return corredorRepo.findAll();
    }

    public Corredor findById(long id) {
        return corredorRepo.findById(id).orElse(null);
    }

    public Corredor save(Corredor corredor) {
        Pais pais = paisRepo.findById(corredor.getIdPais()).orElse(null);
        corredor.setPais(pais);
        return corredorRepo.save(corredor);
    }

    public Corredor update(long id,Corredor corredor) {
        Corredor corredorOld = corredorRepo.findById(id).orElse(null);
        Pais pais = paisRepo.findById(corredor.getIdPais()).orElse(null);
        if (corredorOld != null && pais != null) {
            corredorOld.setNombre(corredor.getNombre());
            corredorOld.setApellido(corredor.getApellido());
            corredorOld.setFechaNacimiento(corredor.getFechaNacimiento());
            corredorOld.setPais(pais);
            return corredorRepo.save(corredorOld);
        }
        throw new RuntimeException();
    }

    public Corredor delete(long id){
        Corredor corredorOld = corredorRepo.findById(id).orElse(null);
        if(corredorOld != null){
            corredorRepo.delete(corredorOld);
            return corredorOld;
        }
        throw new RuntimeException();
    }
}
