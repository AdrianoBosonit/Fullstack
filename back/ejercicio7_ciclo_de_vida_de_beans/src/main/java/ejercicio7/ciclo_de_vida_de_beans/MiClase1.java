package ejercicio7.ciclo_de_vida_de_beans;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MiClase1 {
    @PostConstruct
    void run(){
        System.out.println("Hola desde clase inicial");
    }
}
