package com.uptc.frw.vueltaacolombiarest.controller;

import com.uptc.frw.vueltaacolombiarest.modelo.Etapa;
import com.uptc.frw.vueltaacolombiarest.servicios.EtapaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("etapa")
public class EtapaController {

    @Autowired
    private EtapaService etapaService;

    @GetMapping
    public List<Etapa> findAll() {
        return etapaService.findAll();
    }

    @GetMapping("/{id}")
    public Etapa findById(@PathVariable long id) {
        return etapaService.findById(id);
    }

    @PostMapping
    public Etapa save(@RequestBody Etapa etapa) {
        return etapaService.save(etapa);
    }

    @PutMapping
    public Etapa update(@RequestParam long id,@RequestBody Etapa etapa) {
        return etapaService.update(id, etapa);
    }

    @DeleteMapping("/{id}")
    public Etapa delete(@PathVariable long id) {
        return etapaService.delete(id);
    }
}
