package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;


@RestController
public class Controlador1 {

    private static final String template = "Hola, %s!";
    ObjectMapper object=new ObjectMapper();

    @GetMapping("/user")
    public String user(@RequestParam(value = "name", defaultValue = "Mundo") String name) {
        return String.format(template, name);
    }

    @PostMapping("/useradd")
    public Persona userAdd(@RequestBody String jsonParam)  throws JsonProcessingException {

        Persona persona = object.readValue(jsonParam,Persona.class);
        persona.setEdad(persona.getEdad()+1);
        return persona;

    }
}
