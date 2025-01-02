package com.uptc.frw.vueltaacolombiarest.controller;

import com.uptc.frw.vueltaacolombiarest.model.Corredor;
import com.uptc.frw.vueltaacolombiarest.services.CorredorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("corredor")
public class CorredorController {

    @Autowired
    private CorredorService corredorService;

    @GetMapping
    public List<Corredor> findAll() {
        return corredorService.findAll();
    }

    @GetMapping("/{id}")
    public Corredor findById(@PathVariable long id) {
        return corredorService.findById(id);
    }

    @PostMapping
    public Corredor create(@RequestBody Corredor corredor) {
        return corredorService.save(corredor);
    }

    @PutMapping
    public Corredor update(@RequestParam long id,@RequestBody Corredor corredor) {
        return corredorService.update(id, corredor);
    }

    @DeleteMapping("/{id}")
    public Corredor delete(@PathVariable long id) {
        return corredorService.delete(id);
    }
}
