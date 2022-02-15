package ejercicio8.fichero_application_properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ConfigurationProperties(prefix="datos")
public class VariablesYaml {
    private String VAR1;
    private String My_VAR2;
    private String var3;

    public String getVAR1() {
        return VAR1;
    }

    public void setVAR1(String VAR1) {
        this.VAR1 = VAR1;
    }

    public String getMy_VAR2() {
        return My_VAR2;
    }

    public void setMy_VAR2(String my_VAR2) {
        My_VAR2 = my_VAR2;
    }

    public String getVar3() {
        return Optional.ofNullable(var3).orElse("var3 no tiene valor");
    }

    public void setVar3(String var3) {
        this.var3 = var3;
    }
}
