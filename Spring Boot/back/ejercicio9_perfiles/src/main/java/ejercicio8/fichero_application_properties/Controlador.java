package ejercicio8.fichero_application_properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controlador {

    @Autowired
    Perfiles perfil;
    @Autowired
    VariablesApplicationProperties val = new VariablesApplicationProperties();

    @Autowired
    VariablesMiconfigurationProperties var = new VariablesMiconfigurationProperties();

    @GetMapping("/parametros")
    public String enviarInf() {
        return "El usuario es: " + val.getUsuario() + " y su contraseña: " + val.getPassword();
    }

    @GetMapping("/miconfiguracion")
    public String enviarInfDeMiconfiguracion() {
        return "El valor1 es: " + var.getValor1() + " y el valor2 es : " + var.getValor2();
    }

    @GetMapping("/perfil")
    public void enviarPerfil() {
        perfil.miFuncion();
    }

}
