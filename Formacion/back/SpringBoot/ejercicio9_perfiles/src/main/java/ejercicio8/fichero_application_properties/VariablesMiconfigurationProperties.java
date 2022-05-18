package ejercicio8.fichero_application_properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:miconfiguracion.properties")
@Component
public @Data class VariablesMiconfigurationProperties {
    @Value("${valor1}")
    private String valor1;

    @Value("${valor2}")
    private String valor2;


}
