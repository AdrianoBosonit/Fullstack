package ejercicio8.fichero_application_properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public @Data
class VariablesApplicationProperties {
    @Value("${VAR1}")
    private String variable1;

    @Value("${My.VAR2}")
    private String variable2;

    @Value("${VAR3:var3 no tiene valor}")
    private String variable3;
}
