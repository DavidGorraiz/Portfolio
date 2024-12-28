package com.uptc.frw.vueltaacolombiarest.servicios;

import com.uptc.frw.vueltaacolombiarest.modelo.Edicion;
import com.uptc.frw.vueltaacolombiarest.modelo.Etapa;
import com.uptc.frw.vueltaacolombiarest.repositorio.EdicionRepo;
import com.uptc.frw.vueltaacolombiarest.repositorio.EtapaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtapaService {

    @Autowired
    private EtapaRepo etapaRepo;

    @Autowired
    private EdicionRepo edicionRepo;

    public List<Etapa> findAll() {
        return etapaRepo.findAll();
    }

    public Etapa findById(long id) {
        return etapaRepo.findById(id).orElse(null);
    }

    public Etapa save(Etapa etapa) {
        Edicion edicion = edicionRepo.findById(etapa.getIdEdicion()).orElse(null);
        etapa.setEdicion(edicion);
        return etapaRepo.save(etapa);
    }

    public Etapa update(long id, Etapa etapa) {
        Etapa etapaOld = etapaRepo.findById(id).orElse(null);
        Edicion edicion = edicionRepo.findById(etapa.getIdEdicion()).orElse(null);
        if (etapaOld != null) {
            etapaOld.setEdicion(edicion);
            etapaOld.setOrigen(etapa.getOrigen());
            etapaOld.setDestino(etapa.getDestino());
            etapaOld.setLongitud(etapa.getLongitud());
            etapaOld.setTipoEtapa(etapa.getTipoEtapa());
            return etapaRepo.save(etapaOld);
        }
        throw new RuntimeException();
    }

    public Etapa delete(long id) {
        Etapa etapa = etapaRepo.findById(id).orElse(null);
        if(etapa != null) {
            etapaRepo.delete(etapa);
            return etapa;
        }
        throw new RuntimeException();
    }
}
