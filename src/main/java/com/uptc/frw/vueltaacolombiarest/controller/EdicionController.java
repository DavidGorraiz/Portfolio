package com.uptc.frw.vueltaacolombiarest.controller;

import com.uptc.frw.vueltaacolombiarest.model.Edicion;
import com.uptc.frw.vueltaacolombiarest.services.EdicionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("edicion")
public class EdicionController {

    @Autowired
    private EdicionService edicionService;

    @GetMapping
    public List<Edicion> findAll(){
        return edicionService.findAll();
    }

    @GetMapping("/{id}")
    public Edicion findById(@PathVariable long id){
        return edicionService.findById(id);
    }

    @PostMapping
    public Edicion create(@RequestBody Edicion edicion){
        return edicionService.save(edicion);
    }

    @PutMapping
    public Edicion update(@RequestParam long id,@RequestBody Edicion edicion){
        return edicionService.update(id, edicion);
    }

    @DeleteMapping("/{id}")
    public Edicion delete(@PathVariable long id){
        return edicionService.delete(id);
    }
}
