package ejercicio6.ejercicio6_avanzando_con_los_controladores;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data
class Persona {
    private int id;
    private String nombre;
    private String ciudad;
    private String edad;

    public Persona() {
        this.id = -1;
        this.nombre = null;
        this.ciudad = null;
        this.edad = null;

    }

    public Persona(int id, String nombre, String ciudad, String edad) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.edad = edad;
    }

    public Persona(int id, String nombre, String edad) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = null;
        this.edad = edad;
    }

}
