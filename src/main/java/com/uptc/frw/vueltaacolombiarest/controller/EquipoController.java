package com.uptc.frw.vueltaacolombiarest.controller;

import com.uptc.frw.vueltaacolombiarest.modelo.Equipo;
import com.uptc.frw.vueltaacolombiarest.servicios.EquipoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("equipo")
public class EquipoController {
    @Autowired
    private EquipoServicio equipoServicio;

    @GetMapping
    public List<Equipo> findAll() {
        return equipoServicio.findAll();
    }

    @GetMapping("/{id}")
    public Equipo findById(@PathVariable long id) {
        return equipoServicio.findById(id);
    }

    @PostMapping
    public Equipo save(@RequestBody Equipo equipo) {
        return equipoServicio.save(equipo);
    }

    @PutMapping
    public Equipo update(@RequestParam long id,@RequestBody Equipo equipo) {
        return equipoServicio.update(id, equipo);
    }

    @DeleteMapping("/{id}")
    public Equipo delete(@PathVariable long id) {
        return equipoServicio.delete(id);
    }
}
