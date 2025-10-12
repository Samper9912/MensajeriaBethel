package com.jefferson.MensajeriaBethel.repositories;

import com.jefferson.MensajeriaBethel.models.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    // No hace falta nada extra, JpaRepository ya tiene save, findAll, etc.
}
