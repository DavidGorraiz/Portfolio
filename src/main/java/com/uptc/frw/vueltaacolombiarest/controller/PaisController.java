package com.uptc.frw.vueltaacolombiarest.controller;

import com.uptc.frw.vueltaacolombiarest.modelo.Pais;
import com.uptc.frw.vueltaacolombiarest.servicios.PaisServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pais")
public class PaisController {

    @Autowired
    private PaisServicio paisServicio;

    @GetMapping
    public List<Pais> findAll() {
        return paisServicio.getAll();
    }

    @GetMapping("/{id}")
    public Pais findById(@PathVariable long id) {
        return paisServicio.getById(id);
    }

    @PostMapping
    public Pais save(@RequestBody Pais pais) {
        return paisServicio.save(pais);
    }

    @PutMapping
    public Pais update(@RequestParam long id,@RequestBody Pais pais) {
        return paisServicio.update(id, pais);
    }

    @DeleteMapping("/{id}")
    public Pais delete(@PathVariable long id) {
        return paisServicio.delete(id);
    }
}
