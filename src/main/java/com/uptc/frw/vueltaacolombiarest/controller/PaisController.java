package com.uptc.frw.vueltaacolombiarest.controller;

import com.uptc.frw.vueltaacolombiarest.model.Pais;
import com.uptc.frw.vueltaacolombiarest.services.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pais")
public class PaisController {

    @Autowired
    private PaisService paisService;

    @GetMapping
    public List<Pais> findAll() {
        return paisService.getAll();
    }

    @GetMapping("/{id}")
    public Pais findById(@PathVariable long id) {
        return paisService.getById(id);
    }

    @PostMapping
    public Pais save(@RequestBody Pais pais) {
        return paisService.save(pais);
    }

    @PutMapping
    public Pais update(@RequestParam long id,@RequestBody Pais pais) {
        return paisService.update(id, pais);
    }

    @DeleteMapping("/{id}")
    public Pais delete(@PathVariable long id) {
        return paisService.delete(id);
    }
}
