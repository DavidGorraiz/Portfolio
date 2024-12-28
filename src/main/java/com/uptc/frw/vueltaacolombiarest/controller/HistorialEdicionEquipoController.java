package com.uptc.frw.vueltaacolombiarest.controller;

import com.uptc.frw.vueltaacolombiarest.modelo.HistorialEdicionEquipos;
import com.uptc.frw.vueltaacolombiarest.modelo.Key.HistorialEdicionEquiposKey;
import com.uptc.frw.vueltaacolombiarest.servicios.HistorialEdicionEquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("historialEdicionEquipo")
public class HistorialEdicionEquipoController {

    @Autowired
    private HistorialEdicionEquipoService historialEdicionEquipoService;

    @GetMapping
    public List<HistorialEdicionEquipos> findAll(){
        return historialEdicionEquipoService.findAll();
    }

    @GetMapping("/{idEquipo}/{idEdicion}/{idPatrocinador}")
    public HistorialEdicionEquipos findById(@PathVariable long idEquipo, @PathVariable long idEdicion, @PathVariable long idPatrocinador){
        return historialEdicionEquipoService.findById(idEquipo, idEdicion, idPatrocinador);
    }

    @PostMapping
    public HistorialEdicionEquipos save(@RequestBody HistorialEdicionEquipos historialEdicionEquipo){
        return historialEdicionEquipoService.save(historialEdicionEquipo);
    }
}
