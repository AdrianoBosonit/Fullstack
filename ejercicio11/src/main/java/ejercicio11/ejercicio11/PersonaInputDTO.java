package ejercicio11.ejercicio11;

import lombok.Data;

import javax.persistence.GeneratedValue;
import java.util.Date;

@Data
public class PersonaInputDTO {
    private Integer id;
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;

}
