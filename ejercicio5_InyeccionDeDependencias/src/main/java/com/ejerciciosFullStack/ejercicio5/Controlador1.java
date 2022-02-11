package com.ejerciciosFullStack.ejercicio5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controlador1 {

    @Autowired
    PersonaService personaService;

    @GetMapping("/addPersona")
    public Persona user(@RequestParam(value = "nombre") String nombre,@RequestParam(value = "ciudad") String ciudad
    ,@RequestParam(value = "edad") String edad) {

        return personaService.crearPersona(nombre,ciudad,Integer.parseInt(edad));
    }


}
