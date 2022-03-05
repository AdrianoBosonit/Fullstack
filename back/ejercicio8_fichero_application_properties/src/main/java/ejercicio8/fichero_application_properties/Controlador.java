package ejercicio8.fichero_application_properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class Controlador {
    @Autowired
    VariablesApplicationProperties variables=new VariablesApplicationProperties();

    @Autowired
    VariablesYaml var=new VariablesYaml();

    @GetMapping("/valores")
    public String enviarInf() {
        return "valor de var1 es: " + variables.getVariable1() + " valor de my.var2 es: " + variables.getVariable2();

    }

    @GetMapping("/var3")
    public String enviarInfValor3() {
        return "valor de la var3 es:" +variables.getVariable3();

    }

}
