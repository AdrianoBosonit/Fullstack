package com.ejerciciosFullStack.ejercicio5;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class Ejercicio5Application {


    public static void main(String[] args) {
        SpringApplication.run(Ejercicio5Application.class, args);
    }


    @Bean
    ArrayList<CiudadService> getCiudades() {
        ArrayList<CiudadService> ciudades = new ArrayList<>();
        return ciudades;
    }


    @Bean
    @Qualifier("bean1")
    PersonaService getPersonaBean1(){
        PersonaService PersonaBean1=new PersonaServiceImpl();
        PersonaBean1.setNombre("bean1");
        return PersonaBean1;
    }
    @Bean
    @Qualifier("bean2")
    PersonaService getPersonaBean2(){
        PersonaService PersonaBean2=new PersonaServiceImpl();
        PersonaBean2.setNombre("bean2");
        return PersonaBean2;
    }
    @Bean
    @Qualifier("bean3")
    PersonaService getPersonaBean3(){
        PersonaService PersonaBean3=new PersonaServiceImpl();
        PersonaBean3.setNombre("bean3");
        return PersonaBean3;
    }
}
