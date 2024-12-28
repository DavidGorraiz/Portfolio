package com.uptc.frw.vueltaacolombiarest.repositorio;

import com.uptc.frw.vueltaacolombiarest.modelo.HistorialEdicionEquipos;
import com.uptc.frw.vueltaacolombiarest.modelo.Key.HistorialEdicionEquiposKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistorialEdicionEquipoRepo extends JpaRepository<HistorialEdicionEquipos, HistorialEdicionEquiposKey> {
}
