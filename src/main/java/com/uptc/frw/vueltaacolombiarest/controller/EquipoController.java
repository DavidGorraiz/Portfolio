package com.uptc.frw.vueltaacolombiarest.controller;

import com.uptc.frw.vueltaacolombiarest.model.Equipo;
import com.uptc.frw.vueltaacolombiarest.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("equipo")
public class EquipoController {
    @Autowired
    private EquipoService equipoService;

    @GetMapping
    public List<Equipo> findAll() {
        return equipoService.findAll();
    }

    @GetMapping("/{id}")
    public Equipo findById(@PathVariable long id) {
        return equipoService.findById(id);
    }

    @PostMapping
    public Equipo save(@RequestBody Equipo equipo) {
        return equipoService.save(equipo);
    }

    @PutMapping
    public Equipo update(@RequestParam long id,@RequestBody Equipo equipo) {
        return equipoService.update(id, equipo);
    }

    @DeleteMapping("/{id}")
    public Equipo delete(@PathVariable long id) {
        return equipoService.delete(id);
    }
}
