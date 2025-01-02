package com.uptc.frw.vueltaacolombiarest.controller;

import com.uptc.frw.vueltaacolombiarest.model.Patrocinador;
import com.uptc.frw.vueltaacolombiarest.services.PatrocinadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patrocinador")
public class PatrocinadorController {

    @Autowired
    private PatrocinadorService patrocinadorService;

    @GetMapping
    public List<Patrocinador> findAll() {
        return patrocinadorService.findAll();
    }

    @GetMapping("/{id}")
    public Patrocinador findById(@PathVariable long id) {
        return patrocinadorService.findById(id);
    }

    @PostMapping
    public Patrocinador create(@RequestBody Patrocinador patrocinador) {
        return patrocinadorService.save(patrocinador);
    }

    @PutMapping
    public Patrocinador update(@RequestParam long id,@RequestBody Patrocinador patrocinador) {
        return patrocinadorService.update(id,patrocinador);
    }

    @DeleteMapping("/{id}")
    public Patrocinador delete(@PathVariable long id) {
        return patrocinadorService.delete(id);
    }
}
