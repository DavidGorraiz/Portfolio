package com.uptc.frw.vueltaacolombiarest.repository;

import com.uptc.frw.vueltaacolombiarest.model.HistorialEdicionEquipos;
import com.uptc.frw.vueltaacolombiarest.model.Key.HistorialEdicionEquiposKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistorialEdicionEquipoRepo extends JpaRepository<HistorialEdicionEquipos, HistorialEdicionEquiposKey> {
}
