package com.ejerciciosFullStack.ejercicio5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/controlador1")
public class Controlador1 {

    ObjectMapper object = new ObjectMapper();
    @Autowired
    @Qualifier("PersonaServiceImpl")
    PersonaService personaService;

    @Autowired
    CiudadService ciudadService;

    @Autowired
    ArrayList<CiudadService> ciudades;

    @Autowired
    @Qualifier("bean1")
    PersonaService PersonaBean1;

    @Autowired
    @Qualifier("bean2")
    PersonaService PersonaBean2;

    @Autowired
    @Qualifier("bean3")
    PersonaService PersonaBean3;

    @GetMapping("/addPersona")
    public Persona user(@RequestParam(value = "nombre") String nombre, @RequestParam(value = "ciudad") String ciudad
            , @RequestParam(value = "edad") String edad) {
        return personaService.crearPersona(nombre, ciudad, Integer.parseInt(edad));
    }


    @PostMapping("/addCiudad")
    public void addCiudad(@RequestHeader(value = "nombre") String nombre
            , @RequestHeader(value = "numeroHabitantes") String numeroHabitantes) {
        ciudadService.crearCiudad(nombre, Integer.parseInt(numeroHabitantes));
        ciudades.add(ciudadService);
        System.out.println(ciudadService.getCiudaddes(ciudades));
    }

    @GetMapping("/bean/{bean}")
    public Persona devuelveBean(@PathVariable String bean) {
        switch (bean) {
            case "bean1":
                System.out.println("Hola " + PersonaBean1.getPersona());
                return PersonaBean1.getPersona();
            case "bean2":
                return PersonaBean2.getPersona();
            case "bean3":
                return PersonaBean3.getPersona();
        }
        return null;

    }


}
