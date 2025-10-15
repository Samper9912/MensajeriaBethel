package com.jefferson.MensajeriaBethel.models;

import jakarta.persistence.*;

@Entity
@Table(name = "trabajador")
public class Trabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(nullable = false, unique = true, length = 100)
    private String correo;

    @Column(length = 20)
    private String telefono;

    @Column(length = 50)
    private String cargo;

    @Column(length = 50)
    private String materia;

    @Column(nullable = false)
    private Boolean esDocente;

    // Constructor vacío
    public Trabajador() {}

    // Constructor con parámetros
    public Trabajador(String nombres, String correo, String telefono, String cargo, String materia, Boolean esDocente) {
        this.nombres = nombres;
        this.correo = correo;
        this.telefono = telefono;
        this.cargo = cargo;
        this.materia = materia;
        this.esDocente = esDocente;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public String getMateria() { return materia; }
    public void setMateria(String materia) { this.materia = materia; }
    public Boolean getEsDocente() { return esDocente; }
    public void setEsDocente(Boolean esDocente) { this.esDocente = esDocente; }
}
