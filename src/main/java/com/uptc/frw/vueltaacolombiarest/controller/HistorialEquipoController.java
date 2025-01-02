package com.uptc.frw.vueltaacolombiarest.controller;

import com.uptc.frw.vueltaacolombiarest.model.HistorialEquipos;
import com.uptc.frw.vueltaacolombiarest.services.HistorialEquiposService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("historialEquipo")
public class HistorialEquipoController {

    @Autowired
    private HistorialEquiposService historialEquiposService;

    @GetMapping
    public List<HistorialEquipos> findAll() {
        return historialEquiposService.findAll();
    }

    @GetMapping("/{id}")
    public HistorialEquipos findById(@PathVariable long id) {
        return historialEquiposService.findById(id);
    }

    @PostMapping
    public HistorialEquipos save(@RequestBody HistorialEquipos historialEquipos) {
        return historialEquiposService.save(historialEquipos);
    }

    @PutMapping
    public HistorialEquipos update(@RequestParam long id,@RequestBody HistorialEquipos historialEquipos) {
        return historialEquiposService.update(id, historialEquipos);
    }

    @DeleteMapping("/{id}")
    public HistorialEquipos delete(@PathVariable long id) {
        return historialEquiposService.delete(id);
    }
}
