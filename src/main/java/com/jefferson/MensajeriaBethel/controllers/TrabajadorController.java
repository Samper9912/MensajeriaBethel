package com.jefferson.MensajeriaBethel.controllers;

import com.jefferson.MensajeriaBethel.models.Trabajador;
import com.jefferson.MensajeriaBethel.repositories.TrabajadorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class    TrabajadorController {

    private final TrabajadorRepository trabajadorRepository;

    public TrabajadorController(TrabajadorRepository trabajadorRepository) {
        this.trabajadorRepository = trabajadorRepository;
    }

    @GetMapping("/Home")
    public String mostrarTrabajadores(Model model) {

        List<Trabajador> todos = trabajadorRepository.findAll();

        // Separar docentes y no docentes
        List<Trabajador> docentes = todos.stream()
                .filter(Trabajador::getEsDocente)
                .collect(Collectors.toList());

        List<Trabajador> noDocentes = todos.stream()
                .filter(t -> !t.getEsDocente())
                .collect(Collectors.toList());

        // Agrupar docentes por materia
        Map<String, List<Trabajador>> docentesPorMateria = docentes.stream()
                .collect(Collectors.groupingBy(t -> t.getMateria() != null ? t.getMateria() : "Sin materia"));

        // Agrupar no docentes por cargo
        Map<String, List<Trabajador>> noDocentesPorCargo = noDocentes.stream()
                .collect(Collectors.groupingBy(t -> t.getCargo() != null ? t.getCargo() : "Sin cargo"));

        // Ordenar los mapas (opcional, para que se vea más bonito en la vista)
        docentesPorMateria = docentesPorMateria.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldV, newV) -> oldV,
                        LinkedHashMap::new));

        noDocentesPorCargo = noDocentesPorCargo.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldV, newV) -> oldV,
                        LinkedHashMap::new));

        // Enviar los datos al HTML
        model.addAttribute("docentesPorMateriaAttribute", docentesPorMateria);
        model.addAttribute("noDocentesPorCargoAttribute", noDocentesPorCargo);

        return "Home"; // Devuelve la vista Home.html
    }
}
