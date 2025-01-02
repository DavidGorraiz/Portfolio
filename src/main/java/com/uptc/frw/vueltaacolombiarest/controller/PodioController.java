package com.uptc.frw.vueltaacolombiarest.controller;

import com.uptc.frw.vueltaacolombiarest.model.Podio;
import com.uptc.frw.vueltaacolombiarest.services.PodioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("podio")
public class PodioController {

    @Autowired
    private PodioService podioService;

    @GetMapping
    public List<Podio> findAll(){
        return podioService.findAll();
    }

    @GetMapping("/{id}")
    public Podio findById(@PathVariable long id){
        return podioService.findById(id);
    }

    @PostMapping
    public Podio create(@RequestBody Podio podio){
        return podioService.save(podio);
    }

    @PutMapping
    public Podio update(@RequestParam long id,@RequestBody Podio podio){
        return podioService.update(id,podio);
    }

    @DeleteMapping("/{id}")
    public Podio delete(@PathVariable long id){
        return podioService.delete(id);
    }
}
