package com.uptc.frw.vueltaacolombiarest.controller;

import com.uptc.frw.vueltaacolombiarest.modelo.Edicion;
import com.uptc.frw.vueltaacolombiarest.servicios.EdicionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("edicion")
public class EdicionController {

    @Autowired
    private EdicionServicio edicionServicio;

    @GetMapping
    public List<Edicion> findAll(){
        return edicionServicio.findAll();
    }

    @GetMapping("/{id}")
    public Edicion findById(@PathVariable long id){
        return edicionServicio.findById(id);
    }

    @PostMapping
    public Edicion create(@RequestBody Edicion edicion){
        return edicionServicio.save(edicion);
    }

    @PutMapping
    public Edicion update(@RequestParam long id,@RequestBody Edicion edicion){
        return edicionServicio.update(id, edicion);
    }

    @DeleteMapping("/{id}")
    public Edicion delete(@PathVariable long id){
        return edicionServicio.delete(id);
    }
}
