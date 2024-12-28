package com.uptc.frw.vueltaacolombiarest.servicios;

import com.uptc.frw.vueltaacolombiarest.modelo.Corredor;
import com.uptc.frw.vueltaacolombiarest.modelo.Etapa;
import com.uptc.frw.vueltaacolombiarest.modelo.Podio;
import com.uptc.frw.vueltaacolombiarest.repositorio.CorredorRepo;
import com.uptc.frw.vueltaacolombiarest.repositorio.EtapaRepo;
import com.uptc.frw.vueltaacolombiarest.repositorio.PodioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PodioService {

    @Autowired
    private PodioRepo podioRepo;

    @Autowired
    private EtapaRepo etapaRepo;

    @Autowired
    private CorredorRepo corredorRepo;

    public List<Podio> findAll() {
        return podioRepo.findAll();
    }

    public Podio findById(long id) {
        return podioRepo.findById(id).orElse(null);
    }

    public Podio save(Podio podio) {
        Etapa etapa = etapaRepo.findById(podio.getIdEtapa()).orElse(null);
        Corredor corredor = corredorRepo.findById(podio.getIdCorredor()).orElse(null);
        podio.setEtapa(etapa);
        podio.setCorredor(corredor);
        return podioRepo.save(podio);
    }

    public Podio update(long id,Podio podio) {
        Podio podioOld = podioRepo.findById(id).orElse(null);
        Etapa etapa = etapaRepo.findById(podio.getIdEtapa()).orElse(null);
        Corredor corredor = corredorRepo.findById(podio.getIdCorredor()).orElse(null);
        if (podioOld != null && etapa != null && corredor != null) {
            podioOld.setEtapa(etapa);
            podioOld.setCorredor(corredor);
            podioOld.setPosicion(podio.getPosicion());
            podioOld.setTiempo(podio.getTiempo());
            return podioRepo.save(podioOld);
        }
        throw new RuntimeException("Error al actualizar el podio");
    }

    public Podio delete(long id){
        Podio podio = podioRepo.findById(id).orElse(null);
        if (podio != null) {
            podioRepo.delete(podio);
            return podio;
        }
        throw new RuntimeException("Error al eliminar el podio");
    }
}
