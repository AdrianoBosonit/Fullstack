package ejercicio8.fichero_application_properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:application.properties")
@Component
public @Data class VariablesApplicationProperties {
    @Value("${url}")
    private String usuario;

    @Value("${password}")
    private String password;

}
