package com.uptc.frw.vueltaacolombiarest.services;

import com.uptc.frw.vueltaacolombiarest.model.Pais;
import com.uptc.frw.vueltaacolombiarest.repository.PaisRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisService {
    @Autowired
    private PaisRepo paisRepo;

    public List<Pais> getAll() {
        return paisRepo.findAll();
    }

    public Pais getById(long id) {
        return paisRepo.findById(id).orElse(null);
    }

    public Pais save(Pais pais) {
        return paisRepo.save(pais);
    }

    public Pais update(long id,Pais pais) {
        Pais paisOld = paisRepo.findById(id).orElse(null);
        if (paisOld != null) {
            paisOld.setNombre(pais.getNombre());
            return paisRepo.save(paisOld);
        }
        throw new RuntimeException();
    }

    public Pais delete(long id) {
        Pais pais = paisRepo.findById(id).orElse(null);
        if (pais != null) {
            paisRepo.delete(pais);
            return pais;
        }
        throw new RuntimeException();
    }
}
