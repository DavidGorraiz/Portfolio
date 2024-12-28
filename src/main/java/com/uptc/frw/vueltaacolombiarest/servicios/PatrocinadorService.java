package com.uptc.frw.vueltaacolombiarest.servicios;

import com.uptc.frw.vueltaacolombiarest.modelo.Patrocinador;
import com.uptc.frw.vueltaacolombiarest.repositorio.PatrocinadorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatrocinadorService {

    @Autowired
    private PatrocinadorRepo patrocinadorRepo;

    public List<Patrocinador> findAll() {
        return patrocinadorRepo.findAll();
    }

    public Patrocinador findById(long id) {
        return patrocinadorRepo.findById(id).orElse(null);
    }

    public Patrocinador save(Patrocinador patrocinador) {
        return patrocinadorRepo.save(patrocinador);
    }

    public Patrocinador update(long id,Patrocinador patrocinador) {
        Patrocinador patrocinadorOld = patrocinadorRepo.findById(id).orElse(null);
        if (patrocinadorOld != null) {
            patrocinadorOld.setNIT(patrocinador.getNIT());
            patrocinadorOld.setNombre(patrocinador.getNombre());
            return patrocinadorRepo.save(patrocinadorOld);
        }
        throw new RuntimeException();
    }

    public Patrocinador delete(long id) {
        Patrocinador patrocinador = patrocinadorRepo.findById(id).orElse(null);
        if (patrocinador != null) {
            patrocinadorRepo.delete(patrocinador);
            return patrocinador;
        }
        throw new RuntimeException();
    }
}
