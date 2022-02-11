package com.ejerciciosFullStack.ejercicio5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controlador2 {
    ObjectMapper object=new ObjectMapper();
    @Autowired
    PersonaService personaService;

    @GetMapping("/getPersona")
    public Persona getPersona() {

        return new Persona(personaService.getNombre(),personaService.getCiudad(), (personaService.getEdad())*2);
    }
    @PostMapping("/useradd")
    public Persona userAdd(@RequestBody String jsonParam)  throws JsonProcessingException {

        Persona persona = object.readValue(jsonParam,Persona.class);
        persona.setEdad(persona.getEdad()+1);
        return persona;

    }
}
