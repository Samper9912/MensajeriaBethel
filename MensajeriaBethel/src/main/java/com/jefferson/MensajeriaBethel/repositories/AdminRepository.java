package com.jefferson.MensajeriaBethel.repositories;
import com.jefferson.MensajeriaBethel.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    boolean existsByUsuario(String usuario);
    Optional<Admin> findByUsuarioAndContrasena(String usuario, String contrasena);

}
