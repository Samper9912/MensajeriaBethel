package com.jefferson.MensajeriaBethel.repositories;

import com.jefferson.MensajeriaBethel.models.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador, Long> {
    boolean existsByCorreo(String correo);
}
